package application;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;

public class AiSwitchChoice extends AiFinalChoice implements AiChoiceObject<Anature>
{
	private Anature mAnature;

	public AiSwitchChoice(Anature anature)
	{
		super(AiChoice.Item_Consumed);
		setAnature(anature);
	}

	private void setAnature(Anature anature)
	{
		if(anature != null)
		{
			mAnature = anature;
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in AiMoveChoice.java, Method: setAnature(Anature anature), potion value was null.");
		}
	}

	private Anature getAnature()
	{
		return mAnature;
	}

	@Override
	public Anature getChoiceObject()
	{
		return getAnature();
	}
}
