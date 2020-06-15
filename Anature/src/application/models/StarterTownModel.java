package application.models;

import java.io.Serializable;

import application.enums.TrainerIds;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;

public class StarterTownModel implements Serializable
{
	private static final long serialVersionUID = 3041251606796975792L;

	private Trainer mKelly;

	public StarterTownModel()
	{
		if(mKelly == null)
		{
			mKelly = TrainerBuilder.createTrainer(TrainerIds.Kelly, 1, 13, 17);
		}
	}

	public Trainer getKelly()
	{
		return mKelly;
	}
}
