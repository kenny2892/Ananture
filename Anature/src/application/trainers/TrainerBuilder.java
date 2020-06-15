package application.trainers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.DatabaseConnection;
import application.Startup;
import application.anatures.Anature;
import application.anatures.AnatureBuilder;
import application.enums.DatabaseType;
import application.enums.Species;
import application.enums.TrainerIds;
import application.enums.TypeEffectiveness;
import application.enums.items.HealthPotionId;
import application.interfaces.IHealthPotion;
import application.pools.items.HealthPotionPool;
import application.trainers.ai.AIBuilder;

public class TrainerBuilder
{
	private static Random mRandomObject = new Random();

	/*
	 * STATIC PUBLIC METHODS
	 */

	public static Trainer createTrainer(TrainerIds id, int anatureCount, int anatureMinLevel, int anatureMaxLevel)
	{
		return new Trainer(new TrainerDefualtVariables()
		{
			@Override
			public void getVariables()
			{
				try
				{
					Connection connect = DatabaseConnection.dbConnector(DatabaseType.TrainerDatabase);

					String query = "Select * from Trainers Where TrainerID=?";
					PreparedStatement pst = connect.prepareStatement(query);
					pst.setString(1, id.toString());

					ResultSet results = pst.executeQuery();
					if(results.next())
					{
						trainerId = id;
						trainerName = results.getString("Name");
						trainerRewardForDefeat = Integer.parseInt(results.getString("RewardAmount"));
						trainerAantures = parsePartyList(results.getString("Anatures"), anatureCount, anatureMinLevel, anatureMaxLevel);
						trainerHealthPotions = parsePotionList(results.getString("ItemList"));
						trainerCurrentAnature = trainerAantures.get(0);
						trainerAI = new AIBuilder() //
								.withHealthThreshold(Double.parseDouble(results.getString("HealthThreshold"))) //
								.withSwitchThreshold(TypeEffectiveness.valueOf(results.getString("SwitchThreshold"))) //
								.withMoveThreshold(TypeEffectiveness.valueOf(results.getString("MoveThreshold"))) //
								.create();
					}

					results.close();
					pst.close();
				}

				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * STATIC PRIVATE METHODS
	 */

	private static ArrayList<Anature> parsePartyList(String partyString, int anatureCount, int anatureMinLevel, int anatureMaxLevel)
	{
		ArrayList<Anature> party = new ArrayList<Anature>();

		ArrayList<String> partyStrAra = new ArrayList<String>(Arrays.asList(partyString.split(",")));

		for(String anatureStr : partyStrAra)
		{
			int level = mRandomObject.nextInt(anatureMaxLevel - anatureMinLevel) + anatureMinLevel;
			party.add(AnatureBuilder.createAnature(Startup.getPlayerName(), Species.valueOf(anatureStr), level));
		}

		while(party.size() > anatureCount)
		{
			party.remove(mRandomObject.nextInt(party.size()));
		}

		return party;
	}

	private static ArrayList<IHealthPotion> parsePotionList(String potionsString)
	{
		ArrayList<IHealthPotion> items = new ArrayList<IHealthPotion>();

		if(potionsString.length() != 0)
		{
			ArrayList<String> potionsList = new ArrayList<String>(Arrays.asList(potionsString.split(",")));

			for(String potionId : potionsList)
			{
				items.add(HealthPotionPool.getHealthPotion(HealthPotionId.valueOf(potionId)));
			}
		}

		return items;
	}

}