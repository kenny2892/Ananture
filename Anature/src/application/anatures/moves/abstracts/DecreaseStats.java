package application.anatures.moves.abstracts;

import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.IAnature;

public class DecreaseStats extends Move
{
	private static final long serialVersionUID = 2518797984632858807L;
	private Stat[] mStatsToLower;
	private boolean mApplyToTarget;
	
	public DecreaseStats(boolean applyToTarget, Stat... statsToLower)
	{
		if(statsToLower == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "LowerStats move was passed null stats.");
			return;
		}
		
		mStatsToLower = statsToLower;
		mApplyToTarget = applyToTarget;
	}

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		IAnature toUse = source;
		
		if(mApplyToTarget)
		{
			toUse = target;
		}
		
		for(Stat stat : mStatsToLower)
		{
			MoveResources.decreaseStats(toUse, stat);
		}
	}
}