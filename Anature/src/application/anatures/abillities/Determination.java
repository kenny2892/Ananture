package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.interfaces.IAbility;
import application.interfaces.IMove;

public class Determination implements IAbility
{
	public static String activateAbility(Anature userAnature, IMove move, double userOldHp)
	{
		if(hasNull(userAnature, move, userOldHp))
		{
			return "";
		}

		if(move.doesDamage() && (userOldHp == userAnature.getTotalHitPoints()))
		{
			userAnature.updateCurrentHitPoints(1);
			return userAnature.getName() + " survived on 1 hp thanks to their Determination!";
		}

		return "";
	}

	private static boolean hasNull(Anature userAnature, IMove move, double userOldHp)
	{
		if(userAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "userAnature parameter in Determination was null.");
			return true;
		}

		else if(move == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "move parameter in Determination was null.");
			return true;
		}

		return false;
	}

	public AbilityIds getAbilityId()
	{
		return AbilityIds.Determination;
	}

	public String getAbilityName()
	{
		return "Determination";
	}

	public String getAbilityDescription()
	{
		return "Makes the Anature unable to be defeated in a single attack.";
	}

	public boolean happensEveryTurn()
	{
		return false;
	}
}