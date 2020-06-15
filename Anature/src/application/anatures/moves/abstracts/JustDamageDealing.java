package application.anatures.moves.abstracts;

import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.interfaces.IAnature;

public class JustDamageDealing extends Move
{
	private static final long serialVersionUID = 4418390010746315936L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		MoveResources.causeDamage(source, target, this);
	}
}