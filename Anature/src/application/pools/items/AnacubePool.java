package application.pools.items;

import java.util.HashMap;

import application.enums.items.AnacubeId;
import application.items.Anacube;

public class AnacubePool
{
	private static HashMap<AnacubeId, Anacube> mAnacubes;

	/*
	 * PUBLIC STATIC METHODS
	 */

	public static Anacube getAnacube(AnacubeId anacubeId)
	{
		if(anacubeId == null)
		{
			throw new IllegalArgumentException("Passed value \"anacubeId\" was null.");
		}

		return checkPool().get(anacubeId);
	}

	/*
	 * PRIVATE STATIC METHODS
	 */

	private static void generateAnacubes()
	{
		mAnacubes = new HashMap<AnacubeId, Anacube>();

		Anacube anacube = new Anacube(1);
		// anacube.setItemId(ItemIds.Anacube);
		anacube.setItemName("Anacube");
		mAnacubes.put(AnacubeId.Anacube, anacube);

		Anacube superAnacube = new Anacube(1.5);
		// superAnacube.setItemId(ItemIds.Super_Anacube);
		superAnacube.setItemName("Super Anacube");
		mAnacubes.put(AnacubeId.Super_Anacube, superAnacube);

		Anacube hyperAnacube = new Anacube(2);
		// hyperAnacube.setItemId(ItemIds.Hyper_Anacube);
		hyperAnacube.setItemName("Hyper Anacube");
		mAnacubes.put(AnacubeId.Hyper_Anacube, hyperAnacube);

		Anacube maxAnacube = new Anacube(255);
		// maxAnacube.setItemId(ItemIds.Max_Anacube);
		maxAnacube.setItemName("Max Anacube");
		mAnacubes.put(AnacubeId.Max_Anacube, maxAnacube);
	}

	private static HashMap<AnacubeId, Anacube> checkPool()
	{
		if(mAnacubes == null)
		{
			generateAnacubes();
		}

		return mAnacubes;
	}
}
