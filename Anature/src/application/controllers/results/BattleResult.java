package application.controllers.results;

import java.util.HashMap;
import java.util.Map.Entry;

import application.anatures.Anature;
import application.enums.BattleEndMethods;
import application.enums.Species;

public class BattleResult
{
	private BattleEndMethods mEndMethod;
	private HashMap<Anature, Species> mAnaturesToEvolve;
	
	public BattleResult(BattleEndMethods endMethod, HashMap<Anature, Species> anaturesToEvolve)
	{
		mEndMethod = endMethod;
		mAnaturesToEvolve = anaturesToEvolve;
	}
	
	public boolean hasEvolutions()
	{
		return mAnaturesToEvolve.size() > 0;
	}
	
	public BattleEndMethods getBattleEndMethod()
	{
		return mEndMethod;
	}

	public HashMap<Anature, Species> getAnaturesToEvolve()
	{
		return mAnaturesToEvolve;
	}
	
	public Entry<IAnature, Species> popEvolvedAnature()
	{
		if(mAnaturesToEvolve == null || mAnaturesToEvolve.size() <= 0)
		{
			return null;
		}
		
		Entry<IAnature, Species> toReturn = mAnaturesToEvolve.entrySet().iterator().next();
		mAnaturesToEvolve.remove(toReturn.getKey());
		
		return toReturn;
	}
}
