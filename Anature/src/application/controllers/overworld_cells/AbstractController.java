package application.controllers.overworld_cells;

import java.util.Random;

import application.LoggerStartUp;
import application.Player;
import application.Startup;
import application.controllers.ClickQueue;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.TrainerIds;
import application.enums.WarpPoints;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;
import application.views.elements.PlayerSprite;
import application.views.elements.TrainerSprite;
import application.views.elements.WarpPointBox;
import application.views.overworld_cells.AbstractCell;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public abstract class AbstractController
{
	private LoggerStartUp mLogger;
	protected AbstractCell mView;
	protected final int mSpeed = 300; // pixels / second
	protected double mSpeedMultiplier;
	private AnimationTimer mTimer;
	protected PlayerSprite mPlayerView;
	protected Player mPlayerModel;
	protected ClickQueue mClickQueue;

	protected Image mWalkUpImg, mWalkDownImg, mWalkRightImg, mWalkLeftImg, mStandUpImg, mStandDownImg, mStandRightImg, mStandLeftImg;
	
	public AbstractController(LoggerStartUp logger, AbstractCell view, Player playerModel)
	{
		if(view == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Making Starter Town view null.");
			throw new IllegalArgumentException("Making Starter Town view null.");
		}
		
		mView = view;
		mPlayerView = mView.getPlayer();
		mPlayerModel = playerModel;
		mLogger = logger;
		mSpeedMultiplier = 1;
		mClickQueue = new ClickQueue();
		
		mWalkUpImg = new Image(getClass().getResource("/resources/images/player/up_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkDownImg = new Image(getClass().getResource("/resources/images/player/down_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkRightImg = new Image(getClass().getResource("/resources/images/player/right_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkLeftImg = new Image(getClass().getResource("/resources/images/player/left_walk.gif").toExternalForm(), 100.0, 100.0, true, false);

		mStandUpImg = new Image(getClass().getResource("/resources/images/player/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandRightImg = new Image(getClass().getResource("/resources/images/player/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandLeftImg = new Image(getClass().getResource("/resources/images/player/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		
		mView.setSceneOnKeyboardPressed(e -> processKey(e, true));
		mView.setSceneOnKeyboardReleased(e -> processKey(e, false));
		mView.setSceneOnMouseClicked(e -> trainerEvents());
		
		activateTimer();
	}

	protected abstract void timerHook(double elapsedSeconds);

	protected abstract void keyPressHook(KeyEvent event);
	
	public abstract void movePlayer(WarpPoints warpPoint);

	private boolean checkGrassPatch()
	{
		Bounds player = mPlayerView.getBoxBounds();
		boolean result = false;
		
		for(Rectangle toCheck : mView.getGrassPatches())
		{
			result = player.intersects(toCheck.getBoundsInParent());
			
			if(result)
				break;
		}
		
		return result;
	}

	private WarpPointBox checkWarpPoints()
	{
		Bounds player = mPlayerView.getBoxBounds();
		
		for(WarpPointBox toCheck : mView.getWarpPoints())
		{
			if(player.intersects(toCheck.getBoundsInParent()))
			{
				return toCheck;
			}
		}
		
		return null;
	}

	private boolean xCollisionCheck()
	{
		Bounds right = mPlayerView.getRightBounds();
		Bounds upRight = mPlayerView.getUpperRightBounds();
		Bounds botRight = mPlayerView.getLowerRightBounds();
		
		Bounds left = mPlayerView.getLeftBounds();
		Bounds upLeft = mPlayerView.getUpperLeftBounds();
		Bounds botLeft = mPlayerView.getLowerLeftBounds();
		
		for(Rectangle toCheck : mView.getCollisions())
		{
			boolean check = 
					right.intersects(toCheck.getBoundsInParent()) || 
					upRight.intersects(toCheck.getBoundsInParent()) || botRight.intersects(toCheck.getBoundsInParent()) ||
					left.intersects(toCheck.getBoundsInParent()) || 
					upLeft.intersects(toCheck.getBoundsInParent()) || botLeft.intersects(toCheck.getBoundsInParent());

			if(check)
			{
				return false;
			}
		}
		
		return true;
	}

	private boolean yCollisionCheck()
	{
		Bounds top = mPlayerView.getTopBounds();
		Bounds upLeft = mPlayerView.getUpperLeftBounds();
		Bounds upRight = mPlayerView.getUpperRightBounds();
		
		Bounds bot = mPlayerView.getBotBounds();
		Bounds botLeft = mPlayerView.getLowerLeftBounds();
		Bounds botRight = mPlayerView.getLowerRightBounds();
		
		for(Rectangle toCheck : mView.getCollisions())
		{
			boolean check = 
					top.intersects(toCheck.getBoundsInParent()) || 
					upLeft.intersects(toCheck.getBoundsInParent()) || upRight.intersects(toCheck.getBoundsInParent()) ||
					bot.intersects(toCheck.getBoundsInParent()) || 
					botLeft.intersects(toCheck.getBoundsInParent()) || botRight.intersects(toCheck.getBoundsInParent());

			if(check)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private void updateTrainers(double elapsedSeconds)
	{
		for(TrainerSprite trainer : mView.getTrainerSprites())
		{
			int trainerIndex = trainer.getIndex(mView.getBackground());
			int playerIndex = mPlayerView.getIndex(mView.getBackground());

			if(mPlayerView.getBoxY() > trainer.getCollisionY() && playerIndex < trainerIndex)
			{
				mPlayerView.removeFromContainer(mView.getBackground());
				mPlayerView.addToContainer(mView.getBackground(), trainerIndex + 1);
			}

			else if(mPlayerView.getBoxY() <= trainer.getCollisionY() && playerIndex > trainerIndex)
			{
				mPlayerView.removeFromContainer(mView.getBackground());
				mPlayerView.addToContainer(mView.getBackground(), trainerIndex);
			}
			
			trainer.update(mPlayerView, mSpeed, elapsedSeconds);
		}
	}
	
	private void activateTimer()
	{
		mTimer = new AnimationTimer()
		{
			private long mLastUpdate = -1;
			private double mLastWildX = 0, mLastWildY = 0;

			@Override
			public void handle(long now)
			{
				long elapsedNanoSeconds = now - mLastUpdate;

				if(mLastUpdate < 0)
				{
					mLastUpdate = now;
					return;
				}

				double elapsedSeconds = elapsedNanoSeconds / 1000000000.0;
				double deltaX = 0;
				double deltaY = 0;

				timerHook(elapsedSeconds);
				updateTrainers(elapsedSeconds);

				if(mView.mCanMove)
				{
					if(mView.mRight)
					{
						deltaX += mSpeed * mSpeedMultiplier;
					}

					if(mView.mLeft)
					{
						deltaX -= mSpeed * mSpeedMultiplier;
					}

					if(mView.mDown)
					{
						deltaY += mSpeed * mSpeedMultiplier;
					}

					if(mView.mUp)
					{
						deltaY -= mSpeed * mSpeedMultiplier;
					}

					updatePcSprite();

					double oldX = mPlayerView.getX();
					double oldY = mPlayerView.getY();

					
					
					if(LoggerController.isCollisionEnabled())
					{
						mPlayerView.setX(mView.clampRange(mPlayerView.getX() + deltaX * elapsedSeconds, 0, mView.getMapWidth() - mPlayerView.getFitWidth()));
						if(!xCollisionCheck())
						{
							mPlayerView.setX(oldX);
						}

						mPlayerView.setY(mView.clampRange(mPlayerView.getY() + deltaY * elapsedSeconds, 0, mView.getMapHeight() - mPlayerView.getFitHeight()));
						if(!yCollisionCheck())
						{
							mPlayerView.setY(oldY);
						}
					}
					
					else
					{
						mPlayerView.setX(mView.clampRange(mPlayerView.getX() + deltaX * elapsedSeconds, 0, mView.getMapWidth() - mPlayerView.getFitWidth()));
						mPlayerView.setY(mView.clampRange(mPlayerView.getY() + deltaY * elapsedSeconds, 0, mView.getMapHeight() - mPlayerView.getFitHeight()));
					}
					
					if(checkGrassPatch())
					{
						double currX = mPlayerView.getX();
						double currY = mPlayerView.getY();
						
						if(currX > mLastWildX + 100 || currX < mLastWildX - 100 || currY > mLastWildY + 100 || currY < mLastWildY - 100)
						{
							mLastWildX = mPlayerView.getX();
							mLastWildY = mPlayerView.getY();
							
							Random r = new Random();

							if(r.nextInt(100) > 85) // TODO modify encounter rate calculations
							{
								LoggerController.logEvent(LoggingTypes.Misc, "Player has encountered a wild Anature.");
								Trainer wildEncounter = TrainerBuilder.createTrainer(TrainerIds.Wild, 1, 3, 6);
								
								mView.mCanMove = false;
								mView.mUp = false;
								mView.mDown = false;
								mView.mRight = false;
								mView.mLeft = false;
								
								mPlayerView.showEmote();
								
								Platform.runLater(() ->
								{
									try
									{
										Thread.sleep(250);
									}
									
									catch(InterruptedException e)
									{
										LoggerController.logEvent(LoggingTypes.Error, "Sleep when presenting the emote was interrupted.");
									}
									
									Startup.startBattle(wildEncounter);
								});
							}
						}
					}
					
					WarpPointBox warpCheck = checkWarpPoints();
					
					if(warpCheck != null)
					{
						LoggerController.logEvent(LoggingTypes.Misc, "Player has entered a warp point.");
						Startup.changeScene(warpCheck.getSceneType(), warpCheck.getWarpPoint());
						
						mView.mCanMove = false;
					}
				}

				mLastUpdate = now;
			}
		};

		mTimer.start();
	}

	private void processKey(KeyEvent event, boolean on)
	{
		switch(event.getCode())
		{
			case A:

			case LEFT:
				mView.mLeft = on;
				break;

			case D:

			case RIGHT:
				mView.mRight = on;
				break;

			case W:

			case UP:
				mView.mUp = on;
				break;

			case S:

			case DOWN:
				mView.mDown = on;
				break;

			default:
				break;
		}

		if(event.getCode() == KeyCode.BACK_QUOTE && !on)
		{
			mLogger.toggleWindow();
		}

		if(event.isShiftDown())
		{
			mSpeedMultiplier = 1.75;
		}

		else
		{
			mSpeedMultiplier = 1;
		}

		if(!on)
		{
			keyPressHook(event);
			
			if(event.getCode() == KeyCode.E)
			{
				trainerEvents();
			}
		}
	}
	
	private void trainerEvents()
	{
		for(TrainerSprite trainer : mView.getTrainerSprites())
		{
			if(trainer.interact(mPlayerView, mView.getPlayerFacing()) && mClickQueue.isEmpty())
			{
				mView.mCanMove = false;

				String[] dialogue = trainer.getDialogue();
				
				mView.showDialogue(dialogue[0]);
				
				for(int i = 1; i < dialogue.length; i++)
				{
					String toDisplay = dialogue[i];
					mClickQueue.enqueue(() -> mView.showDialogue(toDisplay), "Show Dialogue");
				}

				if(mPlayerModel.canBattle() && trainer.getTrainerModel() != null && trainer.getTrainerModel().canBattle())
				{
					mClickQueue.enqueue(() ->
					{
						mView.mRight = false;
						mView.mLeft = false;
						mView.mDown = false;
						mView.mUp = false;
						mView.mCanMove = true;
						mView.hideDialogue();
						
						Startup.startBattle(trainer.getTrainerModel());
					}, "Start Battle");
				}
				
				else
				{
					mClickQueue.enqueue(() ->
					{
						mView.hideDialogue();
						mView.mCanMove = true;
					}, "End Dialogue");
				}
			}
			
			else
			{
				Runnable toRun = mClickQueue.dequeue();
				
				if(toRun != null)
				{
					toRun.run();
				}
			}
		}
	}

	private void updatePcSprite()
	{
		if(mView.mUp && mView.mDown && mView.mLeft && mView.mRight)
		{
			if(mPlayerView.getImage().equals(mStandDownImg))
				return;

			mPlayerView.setImage(mStandDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mRight && mView.mLeft && mView.mUp)
		{
			if(mPlayerView.getImage().equals(mWalkUpImg))
				return;

			mPlayerView.setImage(mWalkUpImg);
			mView.setPlayerFacing(Direction.Up);
		}

		else if(mView.mRight && mView.mLeft && mView.mDown)
		{
			if(mPlayerView.getImage().equals(mWalkDownImg))
				return;

			mPlayerView.setImage(mWalkDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mUp && mView.mDown && mView.mRight)
		{
			if(mPlayerView.getImage().equals(mWalkRightImg))
				return;

			mPlayerView.setImage(mWalkRightImg);
			mView.setPlayerFacing(Direction.Right);
		}

		else if(mView.mUp && mView.mDown && mView.mLeft)
		{
			if(mPlayerView.getImage().equals(mWalkLeftImg))
				return;

			mPlayerView.setImage(mWalkLeftImg);
			mView.setPlayerFacing(Direction.Left);
		}

		else if((mView.mUp && mView.mDown) || (mView.mRight && mView.mLeft))
		{
			mPlayerView.setImage(mStandDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mRight && !mPlayerView.getImage().equals(mWalkRightImg))
		{
			mPlayerView.setImage(mWalkRightImg);
			mView.setPlayerFacing(Direction.Right);
		}

		else if(mView.mLeft && !mPlayerView.getImage().equals(mWalkLeftImg))
		{
			mPlayerView.setImage(mWalkLeftImg);
			mView.setPlayerFacing(Direction.Left);
		}

		else if(mView.mDown && !mPlayerView.getImage().equals(mWalkDownImg) && !mView.mLeft && !mView.mRight)
		{
			mPlayerView.setImage(mWalkDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mUp && !mPlayerView.getImage().equals(mWalkUpImg) && !mView.mLeft && !mView.mRight)
		{
			mPlayerView.setImage(mWalkUpImg);
			mView.setPlayerFacing(Direction.Up);
		}

		else if(!mView.mUp && !mView.mDown && !mView.mRight && !mView.mLeft)
		{
			switch(mView.getPlayerFacing())
			{
				case Up:
					if(!mPlayerView.getImage().equals(mStandUpImg))
						mPlayerView.setImage(mStandUpImg);
					break;

				case Right:
					if(!mPlayerView.getImage().equals(mStandRightImg))
						mPlayerView.setImage(mStandRightImg);
					break;

				case Left:
					if(!mPlayerView.getImage().equals(mStandLeftImg))
						mPlayerView.setImage(mStandLeftImg);
					break;

				default:
					if(!mPlayerView.getImage().equals(mStandDownImg))
						mPlayerView.setImage(mStandDownImg);
					break;
			}
		}
	}
}
