package application.enums.items;

public enum HealthPotionId
{
	Potion("Potion"),
	Great_Potion("Great Potion"),
	Ultra_Potion("Ultra Potion"),
	Master_Potion("Master_Potion");

	private String mStringValue;

	private HealthPotionId(String stringValue)
	{
		mStringValue = stringValue;
	}

	public String toString()
	{
		return mStringValue;
	}
}
