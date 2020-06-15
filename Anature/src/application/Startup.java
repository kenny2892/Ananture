package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import application.anatures.Anature;
import application.anatures.AnatureBuilder;
import application.controllers.LoggerController;
import application.controllers.results.BattleResult;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.Species;
import application.enums.WarpPoints;
import application.interfaces.ITrainer;
import application.items.Anacube;
import application.player.Player;
import application.pools.ItemPool;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Startup extends Application
{
	private static Stage mStage;
	private static Player mPlayer;
	private static SceneManager mSceneManager;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mPlayer = new Player(null);

		mStage = primaryStage;
		mStage.getIcons().add(new Image(Startup.class.getResourceAsStream("/resources/images/Icon.png")));
		mStage.setTitle("Anature");

		mStage.setMinWidth(640);
		mStage.setMinHeight(360);

		mStage.setWidth(1280);
		mStage.setHeight(720);

		mStage.setOnCloseRequest(event -> System.exit(-3000));
		LoggerController.logEvent(LoggingTypes.Error, "The logger has started.");

		mSceneManager = new SceneManager(mStage);
		changeScene(SceneType.Intro, null);
		mStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void changeScene(SceneType id, WarpPoints warpPoint)
	{
		mSceneManager.changeScene(id, warpPoint, mPlayer);
	}

	public static void startBattle(ITrainer toBattle)
	{
		mSceneManager.loadBattle(mPlayer, toBattle);
	}

	public static void endBattle(BattleResult result)
	{
		mSceneManager.unloadBattle(result, mPlayer);
	}

	public static void createDemo()
	{
		Anature first = AnatureBuilder.createAnature(getPlayerName(), Species.Null, 54);
		first.setName("Main Null");
		mPlayer.addAnatures(first);
		mPlayer.getAnatures().get(0).getStats().addExperience(14601);

		Anature second = AnatureBuilder.createAnature(getPlayerName(), Species.Null, 12);
		second.setName("Other Null");
		mPlayer.addAnatures(second);

		Anature third = AnatureBuilder.createAnature(getPlayerName(), Species.Sardino, 14);
		mPlayer.addAnatures(third);
		mPlayer.getAnatures().get(2).getStats().addExperience(630);

		IAnature fourth = AnatureBuilder.createAnature(Species.Modenine, 16);
		mPlayer.addAnatures(fourth);
		mPlayer.getAnatures().get(2).getStats().addExperience(630);

		mPlayer.getBackpack().addItem(ItemPool.getHealthPotion(ItemIds.Potion));
		mPlayer.getBackpack().addItem(ItemPool.getHealthPotion(ItemIds.Great_Potion));
		mPlayer.getBackpack().addItem(ItemPool.getHealthPotion(ItemIds.Ultra_Potion));
		mPlayer.getBackpack().addItem(ItemPool.getHealthPotion(ItemIds.Master_Potion));

		mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Anacube));
		mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Super_Anacube));
		mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Hyper_Anacube));
		mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Max_Anacube));

		LoggerController.logEvent(LoggingTypes.Misc, "Generated Demo Player");

		changeScene(SceneType.Starter_Town, WarpPoints.Starter_Town_House_1);
	}

	public static String getPlayerName()
	{
		return mPlayer.getName();
	}

	/*
	 * SAVING FUNCTIONALITY
	 */

	private interface SavableItem
	{
		public Object getItem();

		public void setItem(Object object);
	}

	private enum SaveItem implements SavableItem
	{
		Player
		{
			@Override
			public Object getItem()
			{
				return mPlayer;
			}

			@Override
			public void setItem(Object object)
			{
				mPlayer = (Player) object;
			}
		},

		Models
		{
			@Override
			public Object getItem()
			{
				return mSceneManager.getModels();
			}

			@Override
			public void setItem(Object object)
			{
				mSceneManager.setModels(object);
			}
		},

		CurrentSceneType
		{
			@Override
			public Object getItem()
			{
				return mSceneManager.getCurrSceneType();
			}

			@Override
			public void setItem(Object object)
			{
				mSceneManager.setCurrSceneType(object, mPlayer);
			}
		},

		CurrentPlayerDirection
		{
			@Override
			public Object getItem()
			{
				return mSceneManager.getPlayerFacing();
			}

			@Override
			public void setItem(Object object)
			{
				mSceneManager.setPlayerFacing(object);
			}
		},

		CurrentPlayerCoordinates
		{
			@Override
			public Object getItem()
			{
				return mSceneManager.getCoordinates();
			}

			@Override
			public void setItem(Object object)
			{
				mSceneManager.setCoordinates(object);
			}
		}
	}

	public static boolean save()
	{
		boolean result = false;

		File saveFile = selectFileToSave();

		if(saveFile != null)
		{
			ArrayList<Object> itemsToSave = new ArrayList<Object>();

			for(SaveItem item : SaveItem.values())
			{
				itemsToSave.add(item.getItem());
			}

			try
			{
				FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

				out.writeObject(itemsToSave);
				out.close();
				fileOutputStream.close();

				result = true;
			}

			catch(IOException e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Error when saving: " + e.getMessage());
			}
		}

		mSceneManager.saveAndLoadUpdates();

		return result;
	}

	private static File selectFileToSave()
	{
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("MM-dd-yyyy--HH-mm-ss");
		Date date = new Date();
		String dateTime = dateTimeFormatter.format(date);

		File saveDir = new File("./Saves");

		if(!saveDir.exists())
		{
			saveDir.mkdir();
		}

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save Game File");
		chooser.setInitialDirectory(saveDir);
		chooser.setInitialFileName("Save-" + dateTime);
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Save Files", "*.save"));

		return chooser.showSaveDialog(mStage);
	}

	@SuppressWarnings("unchecked")
	public static boolean load(boolean showConfirmation)
	{
		boolean result = false;

		if(showConfirmation && !showLoadConfirmation())
		{
			mSceneManager.saveAndLoadUpdates();
			return result;
		}

		File saveFile = selectFileToLoad();

		if(saveFile != null)
		{
			LoggerController.logEvent(LoggingTypes.Misc, "Loading save file.");

			ArrayList<Object> objRead = new ArrayList<Object>();
			FileInputStream fileInStream;
			ObjectInputStream in;

			mSceneManager.saveAndLoadUpdates();

			try
			{
				fileInStream = new FileInputStream(saveFile);
				in = new ObjectInputStream(fileInStream);

				objRead = (ArrayList<Object>) in.readObject();

				for(SaveItem item : SaveItem.values())
				{
					item.setItem(objRead.remove(0));
				}

				in.close();
				fileInStream.close();

				result = true;
			}

			catch(Exception e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Error when loading save: " + e.getMessage());
			}
		}

		mSceneManager.saveAndLoadUpdates();

		return result;
	}

	private static boolean showLoadConfirmation()
	{
		LoggerController.logEvent(LoggingTypes.Misc, "Asking if user wants to save before loading.");

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Current progress not saved");
		alert.setHeaderText("Would you like to save your current progress first?");

		ButtonType buttonTypeYes = new ButtonType("Yes");
		ButtonType buttonTypeNo = new ButtonType("No");

		alert.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
		ObservableList<ButtonType> btns = alert.getButtonTypes();
		btns.removeAll(ButtonType.OK);

		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == buttonTypeNo)
		{
			return true;
		}

		if(result.get() == buttonTypeYes)
		{
			return save();
		}

		return false;
	}

	private static File selectFileToLoad()
	{
		LoggerController.logEvent(LoggingTypes.Misc, "Selecting file to load.");
		File saveDir = new File(System.getProperty("user.dir") + "/Saves");
		LoggerController.logEvent(LoggingTypes.Misc, "Making saves folder at: " + saveDir.getAbsolutePath());

		if(!saveDir.exists())
		{

			try
			{
				if(saveDir.mkdir())
				{
					LoggerController.logEvent(LoggingTypes.Misc, "Maked saves folder at: " + saveDir.getAbsolutePath());
				}

				else
				{
					throw new Exception("Making of Save Directory returned as false.");
				}
			}

			catch(Exception e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Something went wrong when making the Saves Directory. " + e.toString());

				for(StackTraceElement element : e.getStackTrace())
				{
					LoggerController.logEvent(LoggingTypes.Error, element.toString());
				}

				saveDir = null;
			}
		}

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Save File");
		chooser.setInitialDirectory(saveDir);
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Save Files", "*.save"));

		return chooser.showOpenDialog(mStage);
	}

	public static void newGame()
	{
		// TODO remove starter, tokens, and items after professor is added
		Anature starter = AnatureBuilder.createAnature(getPlayerName(), Species.Sardino, 5);
		mPlayer.addAnatures(starter);
		mPlayer.addTokens(500);

		for(int i = 0; i < 5; i++)
		{
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Super_Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Hyper_Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Max_Anacube));
		}

		LoggerController.logEvent(LoggingTypes.Misc, "Generated New Game");
		changeScene(SceneType.Starter_Town, WarpPoints.Starter_Town_House_1);
	}
}