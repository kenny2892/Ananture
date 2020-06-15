package application.anatures.moves.abstracts;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.enums.StatusEffects;

public class JustStatus extends Move
{
	private static final long serialVersionUID = -8228479854925452367L;
	private double mThreshold;
	private StatusEffects mStatus;

	public JustStatus(StatusEffects status, double thresholdToGoOver)
	{
		if(status == null)
		{
			status = StatusEffects.None;
		}

		mThreshold = thresholdToGoOver;
		mStatus = status;
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		MoveResources.applyStatus(target, mStatus, mThreshold);
	}
}