package application.pools.items;

import application.controllers.BattleController.DisplayItem;
import application.enums.items.AnacubeId;
import application.enums.items.HealthPotionId;
import application.interfaces.IItem;

public class Items
{
	/*
	 * PUBLIC METHODS
	 */

	public static IItem getItem(DisplayItem<?> displayItem)
	{
		if(displayItem == null)
		{
			throw new IllegalArgumentException("The value of \"displayItem\" was null.");
		}

		if(displayItem.getDisplayItem() == null)
		{
			throw new IllegalStateException("The value of displayItem's \"getDisplayItem()\" was null.");
		}

		if(displayItem.getDisplayItem() instanceof HealthPotionId)
		{
			return getItem((HealthPotionId) displayItem.getDisplayItem());
		}

		if(displayItem.getDisplayItem() instanceof AnacubeId)
		{
			return getItem((AnacubeId) displayItem.getDisplayItem());
		}

		throw new IllegalStateException("We have not added an instance check for \"" + displayItem.getDisplayItem().toString() + "\" in this method.");
	}

	public static IItem getItem(HealthPotionId healthPotionId)
	{
		return HealthPotionPool.getHealthPotion(healthPotionId);
	}

	public static IItem getItem(AnacubeId anacubeId)
	{
		return AnacubePool.getAnacube(anacubeId);
	}
}
