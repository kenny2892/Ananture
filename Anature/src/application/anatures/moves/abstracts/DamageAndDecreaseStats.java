package application.anatures.moves.abstracts;

import application.anatures.Anature;
import application.anatures.moves.MoveResources;
import application.enums.Stat;

public class DamageAndDecreaseStats extends DecreaseStats
{
	private static final long serialVersionUID = -7533855793995006397L;

	public DamageAndDecreaseStats(boolean applyToTarget, Stat... statsToDecrease)
	{
		super(applyToTarget, statsToDecrease);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		super.activateMove(source, target);
		MoveResources.causeDamage(source, target, this);
	}
}