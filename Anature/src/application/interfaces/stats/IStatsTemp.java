package application.interfaces.stats;

import application.enums.Stat;
import application.enums.TempStatsStages;

public interface IStatsTemp
{
	/*
	 * PUBLIC GETS
	 */

	public TempStatsStages getTempAttack();

	public TempStatsStages getTempDefense();

	public TempStatsStages getTempSpecialAttack();

	public TempStatsStages getTempSpecialDefense();

	public TempStatsStages getTempSpeed();

	public TempStatsStages getTempAccuracy();

	public TempStatsStages getTempEvasion();

	/*
	 * PUBLIC METHODS
	 */

	public void resetTempStats();

	public boolean increaseTempStat(Stat stat);

	public boolean decreaseTempStat(Stat stat);

}
