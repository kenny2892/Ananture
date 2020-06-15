package application.anatures.moves.abstracts;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;

public class JustDamageDealing extends Move
{
	private static final long serialVersionUID = 4418390010746315936L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		MoveResources.causeDamage(source, target, this);
	}
}