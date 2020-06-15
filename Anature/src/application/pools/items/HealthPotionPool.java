package application.pools.items;

import java.util.HashMap;

import application.enums.items.HealthPotionId;
import application.interfaces.IHealthPotion;
import application.items.HealthPotion;

public class HealthPotionPool
{
	private static HashMap<HealthPotionId, IHealthPotion> mHealPotions;

	/*
	 * PUBLIC STATIC METHODS
	 */

	public static IHealthPotion getHealthPotion(HealthPotionId healthPotionId)
	{
		if(healthPotionId == null)
		{
			throw new IllegalArgumentException("Passed value \"healthPotionId\" was null.");
		}

		return checkPool().get(healthPotionId);
	}

	/*
	 * PRIVATE STATIC METHODS
	 */

	private static void generateHealthPotions()
	{
		mHealPotions = new HashMap<HealthPotionId, IHealthPotion>();

		mHealPotions.put(HealthPotionId.Potion, new HealthPotion() //
				.withHealAmount(20) //
				// .withItemId(HealthPotionId.Potion) //
				.withItemName("Potion") //
				.create());

		mHealPotions.put(HealthPotionId.Great_Potion, new HealthPotion() //
				.withHealAmount(50) //
				// .withItemId(HealthPotionId.Great_Potion) //
				.withItemName("Great Potion") //
				.create());

		mHealPotions.put(HealthPotionId.Ultra_Potion, new HealthPotion() //
				.withHealAmount(200) //
				// .withItemId(HealthPotionId.Ultra_Potion) //
				.withItemName("Ultra Potion") //
				.create());

		mHealPotions.put(HealthPotionId.Master_Potion, new HealthPotion() //
				.withHealAmount(Integer.MAX_VALUE) //
				// .withItemId(HealthPotionId.Master_Potion) //
				.withItemName("Master Potion") //
				.create());

	}

	private static HashMap<HealthPotionId, IHealthPotion> checkPool()
	{
		if(mHealPotions == null)
		{
			generateHealthPotions();
		}

		return mHealPotions;
	}
}
