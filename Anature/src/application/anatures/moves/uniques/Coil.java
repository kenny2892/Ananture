package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.anatures.moves.MoveResources;
import application.enums.Stat;

public class Coil extends Move
{
	private static final long serialVersionUID = -9080529416887418112L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		MoveResources.causeDamage(source, target, this);
		
		if(Math.random() < 0.33)
		{
			MoveResources.decreaseStats(target, Stat.Speed);
		}
	}
}