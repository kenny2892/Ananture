package application.anatures.moves.abstracts;

import application.anatures.moves.MoveResources;
import application.enums.Stat;
import application.interfaces.IAnature;

public class DamageAndDecreaseStats extends DecreaseStats
{
	private static final long serialVersionUID = -7533855793995006397L;

	public DamageAndDecreaseStats(boolean applyToTarget, Stat... statsToDecrease)
	{
		super(applyToTarget, statsToDecrease);
	}

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		super.activateMove(source, target);
		MoveResources.causeDamage(source, target, this);
	}
}
