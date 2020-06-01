package application.interfaces.stats;

import application.anatures.stats.StatsBuilder;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;

public interface IStats extends IStatsBase
{
	/*
	 * PUBLIC GETS
	 */

	public int getLevel();

	public int getTotalExperiencePoints();

	public int getCurrentHitPoints();

	public LevelingSpeed getLevelingSpeed();

	public Natures getNature();

	public int getTotalHitPoints();

	public int getTotalAttack();

	public int getTotalDefense();

	public int getTotalSpecialAttack();

	public int getTotalSpecialDefense();

	public int getTotalSpeed();

	public int getTotalAccuracy();

	public int getTotalEvasion();

	/*
	 * PUBLIC SETS
	 */

	public void setCurrentHitPoints(int hitPoints);

	public int healAnature(int healAmount);

	/*
	 * PUBLIC METHODS
	 */

	public int addExperience(int expeienceGain);

	public int getExperienceProgression();

	public int getRequiredExperience();

	public StatsBuilder getClone();
}
