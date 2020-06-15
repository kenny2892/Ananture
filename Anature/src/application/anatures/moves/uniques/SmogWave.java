package application.anatures.moves.uniques;

import application.anatures.moves.MoveResources;
import application.anatures.moves.abstracts.JustStatus;
import application.enums.Stat;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class SmogWave extends JustStatus
{
	private static final long serialVersionUID = -2287066231088848103L;

	public SmogWave()
	{
		super(StatusEffects.Poison, 0.3);
	}

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		super.activateMove(source, target);
		MoveResources.decreaseStats(target, Stat.Accuracy);
	}
}