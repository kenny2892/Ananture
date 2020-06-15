package application.interfaces;

import java.io.Serializable;

import application.anatures.Anature;
import application.controllers.results.ItemResult;

public interface IItem extends Serializable
{
	public String getItemName();

	public ItemResult useItem(Anature target);
}
