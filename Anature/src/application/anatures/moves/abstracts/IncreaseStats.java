package application.anatures.moves.abstracts;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;

public class IncreaseStats extends Move
{
	private static final long serialVersionUID = 6391155363343567597L;
	private Stat[] mStatsToIncrease;
	private boolean mApplyToTarget;

	public IncreaseStats(boolean applyToTarget, Stat... statsToIncrease)
	{
		if(statsToIncrease == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "IncreaseStats move was passed null stats.");
			return;
		}

		mStatsToIncrease = statsToIncrease;
		mApplyToTarget = applyToTarget;
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		Anature toUse = source;
		
		if(mApplyToTarget)
		{
			toUse = target;
		}
		
		for(Stat stat : mStatsToIncrease)
		{
			MoveResources.decreaseStats(toUse, stat);
		}
	}
}