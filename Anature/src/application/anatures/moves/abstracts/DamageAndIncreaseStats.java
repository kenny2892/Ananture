package application.anatures.moves.abstracts;

import application.anatures.Anature;
import application.anatures.moves.MoveResources;
import application.enums.Stat;

public class DamageAndIncreaseStats extends IncreaseStats
{
	private static final long serialVersionUID = -2520003177066456528L;

	public DamageAndIncreaseStats(boolean applyToTarget, Stat... statsToIncrease)
	{
		super(applyToTarget, statsToIncrease);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		super.activateMove(source, target);
		MoveResources.causeDamage(source, target, this);
	}
}