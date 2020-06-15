package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.enums.Stat;

public class AntlerShot extends Move
{
	private static final long serialVersionUID = -7429832239175947913L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		MoveResources.decreaseStats(target, Stat.Defense);
		MoveResources.increaseStats(target, Stat.Attack);
		MoveResources.causeDamage(source, target, this);
	}
}