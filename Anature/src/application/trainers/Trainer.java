package application.trainers;

import java.io.Serializable;
import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.TrainerIds;
import application.interfaces.AiChoiceObject;
import application.interfaces.IAI;
import application.interfaces.IHealthPotion;
import application.trainers.ai.choice_objects.AiHealthPotionChoice;
import application.trainers.ai.choice_objects.AiMoveChoice;
import application.trainers.ai.choice_objects.AiNoChoice;
import application.trainers.ai.choice_objects.AiSwitchChoice;
import javafx.scene.image.Image;

public class Trainer implements Serializable
{
	private static final long serialVersionUID = 5701627089126907149L;

	private TrainerIds mId;
	private String mName;
	private int mRewardForDefeat;
	private ArrayList<Anature> mAnatures;
	private ArrayList<IHealthPotion> mHealthPotions;
	private Anature mCurrentAnature;
	private IAI mAI;

	public Trainer(TrainerDefualtVariables trainerVariables)
	{
		trainerVariables.getVariables();

		setId(trainerVariables.trainerId);
		setName(trainerVariables.trainerName);
		setRewardForDefeat(trainerVariables.trainerRewardForDefeat);
		setAnatures(trainerVariables.trainerAantures);
		setHealthPotions(trainerVariables.trainerHealthPotions);
		setCurrentAnature(trainerVariables.trainerCurrentAnature);
		setAI(trainerVariables.trainerAI);
	}

	/*
	 * PUBLIC SETS
	 */

	public Trainer setId(TrainerIds trainerId)
	{
		if(trainerId == null)
		{
			throw new IllegalArgumentException("Passed value \"trainerId\" was null.");
		}

		mId = trainerId;

		return this;
	}

	public Trainer setName(String name)
	{
		if(name == null)
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}

		if(name.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}

		mName = name;

		return this;
	}

	public Trainer setRewardForDefeat(int rewardAmount)
	{
		if(rewardAmount < 0)
		{
			throw new IllegalArgumentException("Passed value \"rewardAmount\" was below 0.");
		}

		mRewardForDefeat = rewardAmount;

		return this;
	}

	public Trainer setAnatures(ArrayList<Anature> anatures)
	{
		// TODO talk with team about if we should allow the trainer anatures variable to
		// be empty
		if(anatures == null)
		{
			throw new IllegalArgumentException("Passed value \"anatures\" was null.");
		}

		mAnatures = anatures;

		return this;
	}

	public Trainer setHealthPotions(ArrayList<IHealthPotion> healthPotions)
	{
		if(healthPotions == null)
		{
			throw new IllegalArgumentException("Passed value \"potions\" was null.");
		}

		mHealthPotions = healthPotions;

		return this;
	}

	public Trainer setCurrentAnature(Anature anature)
	{
		if(anature == null)
		{
			throw new IllegalArgumentException("Passed value \"anature\" was null.");
		}

		mCurrentAnature = anature;

		return this;
	}

	public Trainer setAI(IAI ai)
	{
		if(ai == null)
		{
			throw new IllegalArgumentException("Passed value \"ai\" was null.");
		}

		mAI = ai;

		return this;
	}

	/*
	 * PUBLIC GETS
	 */

	public TrainerIds getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}

	public int getRewardForDefeat()
	{
		return mRewardForDefeat;
	}

	public ArrayList<Anature> getAnatures()
	{
		return mAnatures;
	}

	public ArrayList<IHealthPotion> getHealthPotions()
	{
		return mHealthPotions;
	}

	public Anature getCurrentAnature()
	{
		return mCurrentAnature;
	}

	public IAI getAI()
	{
		return mAI;
	}

	/*
	 * PUBLIC METHODS
	 */

	public Image getBattleSprite()
	{
		if(getId() == TrainerIds.Wild)
			return null;

		return new Image(
				getClass().getResource("/resources/images/trainers/" + getId().toString().toLowerCase() + "/" + getId().toString() + ".png").toExternalForm(),
				1000.0, 1000.0, true, false);
	}

	// TODO We need to get rid of this method
	// TODO Should we have the AI decide which Anature to choose next?
	public int getNextAnature(int index)
	{
		index++;
		if(index >= getAnatures().size())
		{
			index = 0;
		}
		return index;
	}

	public int getAnatureIndex(Anature anature)
	{
		return getAnatures().indexOf(anature);
	}

	public boolean canBattle()
	{
		boolean canBattle = false;

		if(getAnatures().size() != 0)
		{
			for(Anature anature : getAnatures())
			{
				if(anature.getStats().getCurrentHitPoints() != 0)
				{
					canBattle = true;
					break;
				}
			}
		}

		return canBattle;
	}

	public AiChoiceObject<?> useTurn(Anature playerAnature)
	{
		boolean willUseHealthPotion = getAI().willUseHealthPotion(getHealthPotions(), getCurrentAnature());

		if(willUseHealthPotion)
			return chooseHealthPotion();

		boolean willSwitchAnature = getAI().willSwitchAnature(getAnatures(), playerAnature, getCurrentAnature());

		if(willSwitchAnature)
			return chooseAnature(playerAnature);

		AiMoveChoice moveChoice = chooseMove(playerAnature);
		if(moveChoice != null)
			return moveChoice;

		return new AiNoChoice();
	}

	public AiSwitchChoice chooseAnature(Anature playerAnature)
	{
		Anature anatureToSwitchTo = getAI().chooseNewAnature(getAnatures(), getCurrentAnature(), playerAnature);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}

	/*
	 * PRIVATE METHODS
	 */

	private AiHealthPotionChoice chooseHealthPotion()
	{
		IHealthPotion healthPotionToUse = getAI().healthPotionToUse(getHealthPotions(), getCurrentAnature());
		AiHealthPotionChoice healthPotionChoice = new AiHealthPotionChoice(healthPotionToUse);
		return healthPotionChoice;
	}

	private AiMoveChoice chooseMove(Anature playerAnature)
	{
		AiMoveChoice moveChoice = getAI().chooseMove(getCurrentAnature(), playerAnature);
		return moveChoice;
	}
}
