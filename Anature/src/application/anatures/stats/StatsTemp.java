package application.anatures.stats;

import java.io.Serializable;

import application.enums.Stat;
import application.enums.TempStatsStages;
import application.interfaces.stats.IStatsTemp;

class StatsTemp implements IStatsTemp, Serializable
{
	private static final long serialVersionUID = 7563828176824900882L;

	private TempStatsStages mTempAttack;
	private TempStatsStages mTempDefense;
	private TempStatsStages mTempSpecialAttack;
	private TempStatsStages mTempSpecialDefense;
	private TempStatsStages mTempSpeed;
	private TempStatsStages mTempAccuracy;
	private TempStatsStages mTempEvasion;

	StatsTemp()
	{
		resetTempStats();
	}

	/*
	 * PUBLIC GETS
	 */

	public TempStatsStages getTempAttack()
	{
		return mTempAttack;
	}

	public TempStatsStages getTempDefense()
	{
		return mTempDefense;
	}

	public TempStatsStages getTempSpecialAttack()
	{
		return mTempSpecialAttack;
	}

	public TempStatsStages getTempSpecialDefense()
	{
		return mTempSpecialDefense;
	}

	public TempStatsStages getTempSpeed()
	{
		return mTempSpeed;
	}

	public TempStatsStages getTempAccuracy()
	{
		return mTempAccuracy;
	}

	public TempStatsStages getTempEvasion()
	{
		return mTempEvasion;
	}

	@Override
	public boolean increaseTempStat(Stat stat)
	{
		switch(stat)
		{
			case Accuracy:
				mTempAccuracy = mTempAccuracy.incrementStage();
				break;

			case Attack:
				mTempAttack = mTempAttack.incrementStage();
				break;

			case Defense:
				mTempDefense = mTempDefense.incrementStage();
				break;

			case Evasion:
				mTempEvasion = mTempEvasion.incrementStage();
				break;

			case SpecialAttack:
				mTempSpecialAttack = mTempSpecialAttack.incrementStage();
				break;

			case SpecialDefense:
				mTempSpecialDefense = mTempSpecialDefense.incrementStage();
				break;

			case Speed:
				mTempSpeed = mTempSpeed.incrementStage();
				break;

			default:
				return false;
		}
		
		return true;
	}

	@Override
	public boolean decreaseTempStat(Stat stat)
	{
		switch(stat)
		{
			case Accuracy:
				mTempAccuracy = mTempAccuracy.decrementStage();
				break;

			case Attack:
				mTempAttack = mTempAttack.decrementStage();
				break;

			case Defense:
				mTempDefense = mTempDefense.decrementStage();
				break;

			case Evasion:
				mTempEvasion = mTempEvasion.decrementStage();
				break;

			case SpecialAttack:
				mTempSpecialAttack = mTempSpecialAttack.decrementStage();
				break;

			case SpecialDefense:
				mTempSpecialDefense = mTempSpecialDefense.decrementStage();
				break;

			case Speed:
				mTempSpeed = mTempSpeed.decrementStage();
				break;

			default:
				return false;
		}
		
		return true;
	}

	/*
	 * PUBLIC METHODS
	 */

	public void resetTempStats()
	{
		mTempAttack = TempStatsStages.zero;
		mTempDefense = TempStatsStages.zero;
		mTempSpecialAttack = TempStatsStages.zero;
		mTempSpecialDefense = TempStatsStages.zero;
		mTempSpeed = TempStatsStages.zero;
		mTempAccuracy = TempStatsStages.zero;
		mTempEvasion = TempStatsStages.zero;
	}

	/*
	 * PACKAGE METHODS
	 */

	double getTempStatModifier(Stat stat)
	{
		switch(stat)
		{
			case Attack:
				return getTempAttack().getModifier();

			case Defense:
				return getTempDefense().getModifier();

			case SpecialAttack:
				return getTempSpecialAttack().getModifier();

			case SpecialDefense:
				return getTempSpecialDefense().getModifier();

			case Speed:
				return getTempSpeed().getModifier();

			default:
				return 1.0;

		}
	}
}
