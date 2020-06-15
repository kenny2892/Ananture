package application.anatures.moves.uniques;

import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.interfaces.IAnature;

public class DoublePunch extends Move
{
	private static final long serialVersionUID = -1750268943625364491L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		MoveResources.causeDamage(source, target, this);
		MoveResources.causeDamage(source, target, this);
	}
}