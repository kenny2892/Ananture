package application.items;

import java.io.Serializable;

import application.interfaces.IItem;

public abstract class Item implements IItem, Serializable
{
	private static final long serialVersionUID = -8921793567635067569L;

	private String mItemName;

	Item()
	{
		mItemName = "";
	}

	/*
	 * PACKAGE SETS
	 */

	void setItemName(String itemName)
	{
		if(itemName == null)
		{
			throw new IllegalArgumentException("Passed value \"itemName\" was null.");
		}

		if(itemName.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"itemName\" was anempty string.");
		}

		mItemName = itemName;
	}

	/*
	 * PUBLIC METHODS
	 */

	public String getItemName()
	{
		return mItemName;
	}
}
