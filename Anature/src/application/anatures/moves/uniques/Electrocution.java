package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.MoveResources;
import application.anatures.moves.abstracts.DamageAndStatus;
import application.enums.Stat;
import application.enums.StatusEffects;

public class Electrocution extends DamageAndStatus
{
	public Electrocution()
	{
		super(StatusEffects.Paralysis, 0.7);
	}

	private static final long serialVersionUID = 5030180131481086156L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		super.activateMove(source, target);
		MoveResources.decreaseStats(target, Stat.Defense);
	}
}