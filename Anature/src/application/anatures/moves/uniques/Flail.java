package application.anatures.moves.uniques;

import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.enums.Stat;
import application.interfaces.IAnature;

public class Flail extends Move
{
	private static final long serialVersionUID = -7249214592980230469L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		int selfDamage = source.getStats().getTotalStat(Stat.HitPoints) / 4;
		source.applyDamage(selfDamage);
		MoveResources.causeDamage(source, target, this);
	}
}