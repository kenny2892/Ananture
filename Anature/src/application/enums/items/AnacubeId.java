package application.enums.items;

public enum AnacubeId
{
	Anacube("Anacube"),
	Super_Anacube("Super Anacube"),
	Hyper_Anacube("Hyper Anacube"),
	Max_Anacube("Max Anacube");

	private String mStringValue;

	private AnacubeId(String stringValue)
	{
		mStringValue = stringValue;
	}

	public String toString()
	{
		return mStringValue;
	}
}
