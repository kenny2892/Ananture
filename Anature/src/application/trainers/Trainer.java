package application.trainers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.DatabaseConnection;
import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AttackEffectiveness;
import application.enums.DatabaseType;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.enums.Species;
import application.enums.TrainerIds;
import application.interfaces.IAI;
import application.interfaces.IAnature;
import application.interfaces.IBuilder;
import application.interfaces.IHealthPotion;
import application.interfaces.ITrainer;
import application.pools.ItemPool;
import application.trainers.ai.AI;

public class Trainer implements IBuilder<ITrainer>
{
	private TrainerBase mTrainer;

	public Trainer()
	{
		genertaeNewTrainer();
	}

	/*
	 * PUBLIC SETS
	 */

	public Trainer withTrainerId(TrainerIds trainerId)
	{
		mTrainer.setTrainerId(trainerId);
		return this;
	}

	public Trainer withTrainerName(String name)
	{
		mTrainer.setTrainerName(name);
		return this;
	}

	public Trainer withAnatureParty(ArrayList<IAnature> anatureParty)
	{
		mTrainer.setAnatureParty(anatureParty);
		return this;
	}

	public Trainer withHealthPotions(ArrayList<IHealthPotion> healthPotionBases)
	{
		mTrainer.setHealthPotions(healthPotionBases);
		return this;
	}

	public Trainer withCurrentAnature(IAnature currentAnature)
	{
		mTrainer.setCurrentAnature(currentAnature);
		return this;
	}

	public Trainer withAI(IAI ai)
	{
		mTrainer.setAI(ai);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public ITrainer create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a Trainer.");
		}

		TrainerBase trainerToReturn = mTrainer;

		genertaeNewTrainer();

		return trainerToReturn;
	}

	/*
	 * STATIC PUBLIC METHODS
	 */

	public static ITrainer createTrainer(TrainerIds id, int anatureCount, int minLevel, int maxLevel)
	{
		// TODO Fully implement the column moveThreshold in the database

		String trainerName = "";
		String partyList = "";
		String itemsList = "";
		String aiHealthThreshold = "";
		String aiSwitchThreshold = "";
		String aiMoveThreshold = "";

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.TrainerDatabase);

			String query = "Select * from Trainers Where TrainerID=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, id.toString());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				trainerName = results.getString("Name");
				partyList = results.getString("Anatures");
				itemsList = results.getString("ItemList");
				aiHealthThreshold = results.getString("HealthThreshold");
				aiSwitchThreshold = results.getString("SwitchThreshold");
				aiMoveThreshold = results.getString("MoveThreshold");
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		ArrayList<IAnature> party = parsePartyList(partyList, anatureCount, minLevel, maxLevel);
		ArrayList<IHealthPotion> potions = parsePotionList(itemsList);
		IAI ai = parseAi(aiHealthThreshold, aiSwitchThreshold, aiMoveThreshold);

		return new Trainer().withTrainerId(id)
				.withTrainerName(trainerName)
				.withAnatureParty(party)
				.withHealthPotions(potions)
				.withCurrentAnature(party.get(0))
				.withAI(ai)
				.create();
	}

	/*
	 * PRIVATE METHODS
	 */

	private void genertaeNewTrainer()
	{
		mTrainer = new TrainerBase();
	}

	private boolean buildIsComplete()
	{
		return mTrainer.canComplete();
	}

	/*
	 * STATIC PRIVATE METHODS
	 */

	private static ArrayList<IHealthPotion> parsePotionList(String itemsString)
	{
		ArrayList<IHealthPotion> items = new ArrayList<IHealthPotion>();

		if(itemsString.length() != 0)
		{
			ArrayList<String> itemsStrAra = new ArrayList<String>(Arrays.asList(itemsString.split(",")));

			for(String itemId : itemsStrAra)
			{
				try
				{
					IHealthPotion toAdd = ItemPool.getHealthPotion(ItemIds.valueOf(itemId)); // TODO redo ItemPool.java file to decouple

					if(toAdd == null)
					{
						LoggerController.logEvent(LoggingTypes.Error, "Trainer Builder tried to retrieve an item with an invalid Id.");
					}

					else
					{
						items.add(toAdd);
					}
				}

				catch(Exception e)
				{
					LoggerController.logEvent(LoggingTypes.Error, "Trainer Builder tried to retrieve an item with an invalid Id.");
				}
			}
		}

		return items;
	}

	private static ArrayList<IAnature> parsePartyList(String partyString, int anatureCount, int anatureMinLevel, int anatureMaxLevel)
	{
		ArrayList<IAnature> party = new ArrayList<IAnature>();

		ArrayList<String> partyStrAra = new ArrayList<String>(Arrays.asList(partyString.split(",")));
		Random levelGen = new Random();

		for(String anatureStr : partyStrAra)
		{
			int level = levelGen.nextInt(anatureMaxLevel - anatureMinLevel) + anatureMinLevel;
			party.add(Anature.createAnature(Species.valueOf(anatureStr), level));
		}

		return party;
	}

	private static IAI parseAi(String aiHealthThreshold, String aiSwitchThreshold, String aiMoveThreshold)
	{
		double healthThreshold = Double.parseDouble(aiHealthThreshold);
		AttackEffectiveness switchThreshold = AttackEffectiveness.valueOf(aiSwitchThreshold);
		AttackEffectiveness moveThreshold = AttackEffectiveness.valueOf(aiMoveThreshold);

		return new AI().withHealthThreshold(healthThreshold)
				.withSwitchThreshold(switchThreshold)
				.withMoveThreshold(moveThreshold)
				.create();
	}

}