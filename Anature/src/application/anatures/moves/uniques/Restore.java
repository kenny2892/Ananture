package application.anatures.moves.uniques;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Restore extends Move
{
	private static final long serialVersionUID = 3220293979535283251L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.updateStatus(StatusEffects.Sleep);
		source.restore();
	}
}