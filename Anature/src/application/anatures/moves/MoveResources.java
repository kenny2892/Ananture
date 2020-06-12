package application.anatures.moves;

import application.enums.Stat;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAnature;

public class MoveResources
{
	public static void increaseStats(IAnature target, Stat... statsToRaise)
	{
		for(Stat stat : statsToRaise)
		{
			target.getStats().increaseTempStat(stat);
		}
	}

	public static void decreaseStats(IAnature target, Stat... statsToLower)
	{
		for(Stat stat : statsToLower)
		{
			target.getStats().decreaseTempStat(stat);
		}
	}

	public static void causeDamage(IAnature source, IAnature target, Move move)
	{
		target.applyDamage(move.calculateDamage(source, target, !move.isPhysicalAttack()));
	}

	public static boolean applyStatus(IAnature target, StatusEffects toApply, double threshold)
	{
		if(canApplyStatus(target, toApply, threshold))
		{
			target.updateStatus(toApply);
			return true;
		}

		return false;
	}

	public static boolean canApplyStatus(IAnature target, StatusEffects toApply, double threshold)
	{
		Type primary = target.getPrimaryType();
		Type secondary = target.getSecondaryType();

		if(Math.random() > threshold && target.getStatus() == StatusEffects.None)
		{
			if(toApply == StatusEffects.Burn && (primary == Type.Fire || secondary == Type.Fire))
			{
				return false;
			}

			else if(toApply == StatusEffects.Poison && (primary == Type.Poison || secondary == Type.Poison))
			{
				return false;
			}

			else if(toApply == StatusEffects.Paralysis && (primary == Type.Electric || secondary == Type.Electric))
			{
				return false;
			}

			return true;
		}

		return false;
	}
}
