package application.items;

import application.Anature;
import application.Item;
import application.enums.ItemIds;

public class MasterPotion extends Item{

	public MasterPotion() {
		super(ItemIds.Master_Potion, "Master Potion"); 
	}

	public void useItem(Anature target)
	{
		System.out.println("Use Item Master Potion"); // TODO
	}
}
