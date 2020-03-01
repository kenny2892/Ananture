package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.Startup;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.models.StarterTownModel;
import application.trainers.Trainer;
import application.views.overworld_cells.StarterTownCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StarterTownController extends AbstractController
{
	private StarterTownCell mView;
	private Trainer mKellyTrainer;

	public StarterTownController(LoggerStartUp logger, StarterTownCell view, StarterTownModel model)
	{
		super(logger, view);

		if(model == null)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Making Starter Town Model null.");
			throw new IllegalArgumentException("Making Starter Town Model null.");
		}

		mView = view;
		mKellyTrainer = model.getKelly();
	}

	@Override
	protected void timerHook()
	{
		int trainerIndex = mView.mKelly.getIndex(mView.getBackground());
		int playerIndex = mPlayer.getIndex(mView.getBackground());

		if(mPlayer.getBoxY() > mView.mKelly.getCollisionY() && playerIndex < trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex + 1);
		}

		else if(mPlayer.getBoxY() <= mView.mKelly.getCollisionY() && playerIndex > trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex);
		}
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		if(event.getCode() == KeyCode.E)
		{
			if(mView.mKelly.interact(mPlayer, mView.getPlayerFacing()) != null && mClickQueue.isEmpty())
			{
				mView.mCanMove = false;

				if(mKellyTrainer.canBattle())
				{
					mView.showDialogue("Hi there, my name is Kelly!");
					mClickQueue.enqueue(() -> mView.showDialogue("Let's battle!"));
					mClickQueue.enqueue(() ->
					{
						mView.mRight = false;
						mView.mLeft = false;
						mView.mDown = false;
						mView.mUp = false;
						mView.mCanMove = true;
						mView.hideDialogue();
						
						Startup.startBattle(mKellyTrainer);
					});
				}
				
				else
				{
					mView.showDialogue("Nice battle!");
					mClickQueue.enqueue(() ->
					{
						mView.hideDialogue();
						mView.mCanMove = true;
					});
				}
			}
			
			else
			{
				mClickQueue.dequeue().run();
			}
		}
	}
}
