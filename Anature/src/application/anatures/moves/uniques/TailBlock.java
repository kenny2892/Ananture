package application.anatures.moves.uniques;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.Stat;

public class TailBlock extends Move
{
	private static final long serialVersionUID = 6498306489816249862L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		// TODO Add in a protect invisible status and remove these stat changes
		source.getStats().increaseTempStat(Stat.Defense);
		source.getStats().increaseTempStat(Stat.SpecialDefense);
	}
}