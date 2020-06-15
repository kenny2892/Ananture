package application.anatures.moves.uniques;

import java.util.Random;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.Stat;
import application.interfaces.stats.IStats;

public class WiredMess extends Move
{
	private static final long serialVersionUID = 214846540864140820L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		Random rng = new Random();
		int firstToDecrease = rng.nextInt(4) + 1;
		int secondToDecrease = findUnique(firstToDecrease, rng);

		decreaseStat(firstToDecrease, target);
		decreaseStat(secondToDecrease, target);
	}

	private int findUnique(int firstToDecrease, Random rng)
	{
		int toReturn = rng.nextInt(4) + 1;

		while(toReturn == firstToDecrease)
		{
			toReturn = rng.nextInt(4) + 1;
		}

		return toReturn;
	}

	private void decreaseStat(int statNumber, Anature toLower)
	{
		IStats stats = toLower.getStats();

		switch(statNumber)
		{
			case 1:
				stats.decreaseTempStat(Stat.Attack);
				break;

			case 2:
				stats.decreaseTempStat(Stat.SpecialAttack);
				break;

			case 3:
				stats.decreaseTempStat(Stat.Defense);
				break;

			case 4:
				stats.decreaseTempStat(Stat.SpecialDefense);
				break;

			default:
				break;
		}
	}
}
