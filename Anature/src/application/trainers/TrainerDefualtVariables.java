package application.trainers;

import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.TrainerIds;
import application.interfaces.IAI;
import application.interfaces.IHealthPotion;

public abstract class TrainerDefualtVariables
{
	public TrainerIds trainerId = null;
	public String trainerName = null;
	public int trainerRewardForDefeat = -1;
	public ArrayList<Anature> trainerAantures = null;
	public ArrayList<IHealthPotion> trainerHealthPotions = null;
	public Anature trainerCurrentAnature = null;
	public IAI trainerAI = null;

	public abstract void getVariables();

}
