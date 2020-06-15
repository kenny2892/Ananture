package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import application.anatures.Anature;
import application.controllers.BattleController;
import application.controllers.IntroController;
import application.controllers.LoggerController;
import application.controllers.menus.AnatureSummaryController;
import application.controllers.menus.EvolutionController;
import application.controllers.overworld_cells.AbstractController;
import application.controllers.overworld_cells.GrassTownController;
import application.controllers.overworld_cells.PathOneController;
import application.controllers.overworld_cells.RestStationController;
import application.controllers.overworld_cells.StarterTownController;
import application.controllers.results.BattleResult;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.Species;
import application.enums.WarpPoints;
import application.models.PathOneModel;
import application.models.StarterTownModel;
import application.player.Player;
import application.trainers.Trainer;
import application.views.overworld_cells.AbstractCell;
import application.views.overworld_cells.GrassTownCell;
import application.views.overworld_cells.PathOneCell;
import application.views.overworld_cells.RestStationCell;
import application.views.overworld_cells.StarterTownCell;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class SceneManager
{
	private Stage mStage;

	private static SceneType mLastSceneType, mCurrSceneType;
	private static AbstractCell mCurrentCell;
	private static AbstractController mCurrentController;

	private Stage mLoggerStage;
	private static LoggerStartUp mLogger;
	private EventHandler<KeyEvent> mConsoleListener;

	private static Scene mAnatureSummaryView;
	private static AnatureSummaryController mAnatureSummaryController;

	private static EvolutionController mEvolutionController;

	private static RestStationCell mRestStationGrassView;
	private static RestStationController mRestStationGrassController;

	private static StarterTownModel mStarterTownModel;
	private static StarterTownCell mStarterTownView;
	private static StarterTownController mStarterTownController;

	private static PathOneModel mPathOneModel;
	private static PathOneCell mPathOneView;
	private static PathOneController mPathOneController;

	private static GrassTownCell mGrassTownView;
	private static GrassTownController mGrassTownController;

	public SceneManager(Stage stage)
	{
		if(stage == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "stage was null when passed to SceneManager.");
			throw new IllegalArgumentException("stage was null when passed to SceneManager.");
		}

		mConsoleListener = event ->
		{
			if(event.getText().compareTo("`") == 0)
			{
				mLogger.toggleWindow();
			}
		};

		try
		{
			mLogger = new LoggerStartUp(mConsoleListener);
			mLogger.init();
			mLoggerStage = new Stage();
			mLogger.start(mLoggerStage);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		mStage = stage;
		mLastSceneType = SceneType.Intro;
		mCurrSceneType = SceneType.Intro;
	}

	public void loadBattle(Player player, Trainer toBattle)
	{
		try
		{
			double width = mStage.getWidth();
			double height = mStage.getHeight();

			FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/application/views/BattleView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.setOnKeyReleased(mConsoleListener);

			BattleController controller = (BattleController) loader.getController();
			controller.setUpBindingsAndElements(scene);
			controller.updateElements(player, toBattle);

			mLastSceneType = mCurrSceneType;

			LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Battle");
			mStage.setScene(scene);

			mStage.setWidth(width);
			mStage.setHeight(height);
		}

		catch(IOException e)
		{
			e.printStackTrace();
			LoggerController.logEvent(LoggingTypes.Error, "Exception when starting battle. \n" + e.getMessage());
		}
	}

	public void unloadBattle(BattleResult result, Player player)
	{
		if(result.hasEvolutions())
		{
			Entry<Anature, Species> evolveEntry = result.popEvolvedAnature();

			changeScene(SceneType.Evolution, null, player);
			mEvolutionController.startEvolution(player.getAnatures(), evolveEntry.getKey(), evolveEntry.getValue(), () ->
			{
				if(result.hasEvolutions())
				{
					unloadBattle(result, player);
				}

				else
				{
					changeScene(null, null, player);
				}
			});
		}

		else
		{
			changeScene(null, null, player);
		}
	}

	// Savability

	public ArrayList<Object> getModels()
	{
		ArrayList<Object> models = new ArrayList<Object>();
		models.add(mStarterTownModel);
		models.add(mPathOneModel);

		return models;
	}

	@SuppressWarnings("unchecked")
	public void setModels(Object object)
	{
		if(object instanceof ArrayList)
		{
			ArrayList<Object> models = (ArrayList<Object>) object;

			mStarterTownModel = models.get(0) instanceof StarterTownModel ? (StarterTownModel) models.get(0) : null;
			mPathOneModel = models.get(0) instanceof PathOneModel ? (PathOneModel) models.get(1) : null;
		}
	}

	public SceneType getCurrSceneType()
	{
		return mCurrSceneType;
	}

	public void setCurrSceneType(Object object, Player player)
	{
		if(object instanceof SceneType)
		{
			mCurrSceneType = (SceneType) object;
			changeScene(mCurrSceneType, null, player);
		}
	}

	public Direction getPlayerFacing()
	{
		return mCurrentCell.getPlayerFacing();
	}

	public void setPlayerFacing(Object object)
	{
		if(object instanceof Direction)
		{
			Direction facing = (Direction) object;
			mCurrentCell.setPlayerFacing(facing);
		}
	}

	public Coordinates getCoordinates()
	{
		return new Coordinates(mCurrentCell.getPlayer().getX(), mCurrentCell.getPlayer().getY());
	}

	public void setCoordinates(Object object)
	{
		if(object instanceof Coordinates)
		{
			Coordinates coords = (Coordinates) object;
			mCurrentCell.getPlayer().setX(coords.x);
			mCurrentCell.getPlayer().setY(coords.y);
		}
	}

	public void saveAndLoadUpdates()
	{
		if(mCurrentController != null)
		{
			mCurrentController.saveLoadUpdates();
		}
	}

	public void changeScene(SceneType id, WarpPoints warpPoint, Player player)
	{
		double width = mStage.getWidth();
		double height = mStage.getHeight();
		boolean canSwitchBackTo = true;

		if(id == null)
		{
			id = mLastSceneType;
		}

		switch(id)
		{
			case Intro:
				canSwitchBackTo = loadIntro();
				break;

			case Anature_Summary:
				try
				{
					canSwitchBackTo = loadAnatureSummary(player);
				}

				catch(IOException e)
				{
					e.printStackTrace();
					LoggerController.logEvent(LoggingTypes.Error, "Error occured when loading Anature Summary.");
				}
				break;

			case Evolution:
				try
				{
					canSwitchBackTo = loadEvolutionView();
				}

				catch(IOException e)
				{
					e.printStackTrace();
					LoggerController.logEvent(LoggingTypes.Error, "Error occured when loading Evolution.");
				}
				break;

			case Starter_Town:
				canSwitchBackTo = loadStarterTown(id, warpPoint, player);
				break;

			case Path_1:
				canSwitchBackTo = loadPathOne(id, warpPoint, player);
				break;

			case Grass_Town:
				canSwitchBackTo = loadGrassTown(id, warpPoint, player);
				break;

			case Rest_Station_Grass_Town:
				canSwitchBackTo = loadRestStation(id, warpPoint, player);
				break;

			default:
				break;
		}

		if(canSwitchBackTo)
		{
			mLastSceneType = mCurrSceneType;
			mCurrSceneType = id;
		}

		mStage.setWidth(width);
		mStage.setHeight(height);
	}

	// Menus

	private boolean loadIntro()
	{
		try
		{
			FXMLLoader introLoader = new FXMLLoader(Startup.class.getResource("/application/views/IntroView.fxml"));
			Parent introRoot = introLoader.load();
			Scene intro = new Scene(introRoot);

			IntroController introController = introLoader.getController();
			introController.updateBinds(intro, mConsoleListener);

			LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Intro");
			mStage.setScene(intro);
		}

		catch(IOException e)
		{
			e.printStackTrace();
			LoggerController.logEvent(LoggingTypes.Error, "Error occured when loading Intro. " + e.getMessage());
		}

		return false;
	}

	private boolean loadAnatureSummary(Player player) throws IOException
	{
		if(mAnatureSummaryView == null || mAnatureSummaryController == null)
		{
			FXMLLoader summaryLoader = new FXMLLoader(Startup.class.getResource("/application/views/AnatureSummaryView.fxml"));
			Parent summaryRoot = summaryLoader.load();
			mAnatureSummaryView = new Scene(summaryRoot);
			mAnatureSummaryView.setOnKeyReleased(mConsoleListener);

			mAnatureSummaryController = summaryLoader.getController();
			mAnatureSummaryController.updateBinds(mAnatureSummaryView);
		}

		mAnatureSummaryController.displayParty(player.getAnatures());

		LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Anature Summary");
		mStage.setScene(mAnatureSummaryView);

		return true;
	}

	private boolean loadEvolutionView() throws IOException
	{
		FXMLLoader evolutionLoader = new FXMLLoader(Startup.class.getResource("/application/views/EvolutionView.fxml"));
		Parent evolutionRoot = evolutionLoader.load();
		Scene evolutionView = new Scene(evolutionRoot);
		evolutionView.setOnKeyReleased(mConsoleListener);

		mEvolutionController = evolutionLoader.getController();
		mEvolutionController.updateBinds(evolutionView);

		LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Evolution Page");
		mStage.setScene(evolutionView);

		return false;
	}

	// Cells

	private void loadCell(AbstractCell cell, AbstractController controller, SceneType id, WarpPoints warpPoint)
	{
		if(mCurrentCell != null)
		{
			cell.setKeysPressed(mCurrentCell.getKeysPressed());
		}

		Scene scene = cell.getScene();
		controller.movePlayer(warpPoint);
		mCurrentCell = cell;
		mCurrentController = controller;

		LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to " + id.toString());
		mStage.setScene(scene);
	}

	private boolean loadStarterTown(SceneType id, WarpPoints warpPoint, Player player)
	{
		if(mStarterTownModel == null)
		{
			mStarterTownModel = new StarterTownModel();
		}

		if(mStarterTownView == null)
		{
			mStarterTownView = new StarterTownCell(mLogger);
		}

		if(mStarterTownController == null)
		{
			mStarterTownController = new StarterTownController(mLogger, mStarterTownView, mStarterTownModel, player);
		}

		loadCell(mStarterTownView, mStarterTownController, id, warpPoint);
		return true;
	}

	private boolean loadPathOne(SceneType id, WarpPoints warpPoint, Player player)
	{
		if(mPathOneModel == null)
		{
			mPathOneModel = new PathOneModel();
		}

		if(mPathOneView == null)
		{
			mPathOneView = new PathOneCell(mLogger);
		}

		if(mPathOneController == null)
		{
			mPathOneController = new PathOneController(mLogger, mPathOneView, mPathOneModel, player);
		}

		loadCell(mPathOneView, mPathOneController, id, warpPoint);
		return true;
	}

	private boolean loadGrassTown(SceneType id, WarpPoints warpPoint, Player player)
	{
		// Model Here

		if(mGrassTownView == null)
		{
			mGrassTownView = new GrassTownCell(mLogger);
		}

		if(mGrassTownController == null)
		{
			mGrassTownController = new GrassTownController(mLogger, mGrassTownView, player);
		}

		loadCell(mGrassTownView, mGrassTownController, id, warpPoint);
		return true;
	}

	private boolean loadRestStation(SceneType id, WarpPoints warpPoint, Player player)
	{
		// Model Here

		if(mRestStationGrassView == null)
		{
			mRestStationGrassView = new RestStationCell(mLogger, id);
		}

		if(mRestStationGrassController == null)
		{
			mRestStationGrassController = new RestStationController(mLogger, mRestStationGrassView, player);
		}

		loadCell(mRestStationGrassView, mRestStationGrassController, id, warpPoint);
		return true;
	}
}
