package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Tackle extends Move
{
	private static final long serialVersionUID = 2327780291255535503L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
	}
}
