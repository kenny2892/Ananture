package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class FactoryReset extends Move
{
	private static final long serialVersionUID = 4892000699183882732L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().resetTempStats();
		source.setStatus(StatusEffects.None);
	}
}