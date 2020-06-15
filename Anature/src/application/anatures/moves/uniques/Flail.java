package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.enums.Stat;

public class Flail extends Move
{
	private static final long serialVersionUID = -7249214592980230469L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		int selfDamage = source.getStats().getTotalStat(Stat.HitPoints) / 4;
		source.applyDamage(selfDamage);
		MoveResources.causeDamage(source, target, this);
	}
}