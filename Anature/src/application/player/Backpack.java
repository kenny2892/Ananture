package application.player;

import java.io.Serializable;
import java.util.ArrayList;

import application.enums.items.AnacubeId;
import application.enums.items.HealthPotionId;
import application.interfaces.IHealthPotion;
import application.interfaces.IItem;
import application.items.Anacube;
import application.pools.items.AnacubePool;
import application.pools.items.HealthPotionPool;

public class Backpack implements Serializable
{
	private static final long serialVersionUID = -2177964342378481427L;

	private ArrayList<IHealthPotion> mHealthPotions;
	private ArrayList<Anacube> mAnacubes;

	public Backpack()
	{
		mHealthPotions = new ArrayList<IHealthPotion>();
		mAnacubes = new ArrayList<Anacube>();
	}

	/*
	 * PUBLIC GETS
	 */

	public ArrayList<IHealthPotion> getHealthPotions()
	{
		return new ArrayList<IHealthPotion>(mHealthPotions);
	}

	public ArrayList<Anacube> getAnacubes()
	{
		return new ArrayList<Anacube>(mAnacubes);
	}

	/*
	 * PUBLIC METHODS
	 */

	/*
	 * addItem METHODS
	 */

	public boolean addItem(IItem item)
	{
		if(item == null)
		{
			throw new IllegalArgumentException("Passed value \"item\" was null.");
		}

		if(item instanceof IHealthPotion)
		{
			return addItem((IHealthPotion) item);
		}

		if(item instanceof Anacube)
		{
			return addItem((Anacube) item);
		}

		return false;
	}

	public boolean addItem(HealthPotionId healthPotionId)
	{
		IHealthPotion healthPotion = HealthPotionPool.getHealthPotion(healthPotionId);

		return addItem(healthPotion);
	}

	public boolean addItem(AnacubeId anacubeId)
	{
		Anacube anacube = AnacubePool.getAnacube(anacubeId);

		return addItem(anacube);
	}

	public boolean addItem(IHealthPotion healthPotion)
	{
		if(healthPotion == null)
		{
			throw new IllegalArgumentException("Passed value \"healthPotion\" was null.");
		}

		return mHealthPotions.add(healthPotion);
	}

	public boolean addItem(Anacube anacube)
	{
		if(anacube == null)
		{
			throw new IllegalArgumentException("Passed value \"anacube\" was null.");
		}

		return mAnacubes.add(anacube);
	}

	/*
	 * removeItem() METHODS
	 */

	public boolean removeItem(IItem item)
	{
		if(item == null)
		{
			throw new IllegalArgumentException("Passed value \"item\" was null.");
		}

		if(item instanceof IHealthPotion)
		{
			return removeItem((IHealthPotion) item);
		}

		if(item instanceof Anacube)
		{
			return removeItem((Anacube) item);
		}

		return false;
	}

	public boolean removeItem(HealthPotionId healthPotionId)
	{
		IHealthPotion healthPotion = HealthPotionPool.getHealthPotion(healthPotionId);

		return removeItem(healthPotion);
	}

	public boolean removeItem(AnacubeId anacubeId)
	{
		Anacube anacube = AnacubePool.getAnacube(anacubeId);

		return removeItem(anacube);
	}

	public int getTotalPotionCount()
	{
		return mHealthPotions.size();
	}

	public boolean removeItem(IHealthPotion healthPotion)
	{
		if(healthPotion == null)
		{
			throw new IllegalArgumentException("Passed value \"healthPotion\" was null.");
		}

		return mHealthPotions.remove(healthPotion);
	}

	public boolean removeItem(Anacube anacube)
	{
		if(anacube == null)
		{
			throw new IllegalArgumentException("Passed value \"anacube\" was null.");
		}

		return mAnacubes.remove(anacube);
	}

	public int getHealthPotionCount(HealthPotionId healthPotionId)
	{
		IHealthPotion healthPotion = HealthPotionPool.getHealthPotion(healthPotionId);

		int healthPotionCount = 0;
		for(IHealthPotion healthPotionInList : getHealthPotions())
		{
			if(healthPotionInList.equals(healthPotion))
				healthPotionCount++;
		}

		return healthPotionCount;
	}

	public int getAnacubeCount(AnacubeId anacubeId)
	{
		Anacube anacube = AnacubePool.getAnacube(anacubeId);

		int anacubeCount = 0;
		for(Anacube anacubeInList : getAnacubes())
		{
			if(anacubeInList.equals(anacube))
				anacubeCount++;
		}

		return anacubeCount;
	}
}