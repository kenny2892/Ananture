package application.anatures.moves.abstracts;

import application.anatures.moves.MoveResources;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class DamageAndStatus extends JustStatus
{
	private static final long serialVersionUID = 3724356003537500882L;

	public DamageAndStatus(StatusEffects status, double thresholdToGoOver)
	{
		super(status, thresholdToGoOver);
	}

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		super.activateMove(source, target);
		MoveResources.causeDamage(source, target, this);
	}
}