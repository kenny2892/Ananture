package application.anatures;

import java.io.Serializable;
import java.util.ArrayList;

import application.anatures.abillities.NullAbility;
import application.anatures.movesets.MoveSet;
import application.anatures.movesets.NullMoveSet;
import application.anatures.stats.NullStats;
import application.enums.BattleAnimationType;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import javafx.scene.image.Image;

class Anature implements IAnature, Serializable
{
	private static final long serialVersionUID = -3387008989524977841L;

	private String mName;
	private String mOwnerName;
	private boolean mIsShiny;
	private Species mSpecies;
	private Gender mGender;
	private Type mPrimaryType;
	private Type mSecondaryType;
	private MoveSet mMoveSet;
	private IAbility mAbility;
	private StatusEffects mStatus;
	private IStats mStats;
	private int mIndexNumber;
	private int mCatchRate;

	Anature()
	{
		mName = "";
		mOwnerName = "";
		mIsShiny = false;
		mSpecies = Species.NotSet;
		mGender = Gender.NotSet;
		mPrimaryType = Type.NotSet;
		mSecondaryType = Type.NotSet;
		mMoveSet = NullMoveSet.getNullMoveSet();
		mAbility = NullAbility.getNullAbility();
		mStatus = StatusEffects.NotSet;
		mStats = NullStats.getNullStats();
		mIndexNumber = -1;
		mCatchRate = -1;
	}

	/*
	 * PACKAGE SETS
	 */

	void setName(String name)
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
	}

	void setOwnerName(String ownerName)
	{
		if(ownerName == null)
		{
			throw new IllegalArgumentException("Passed value \"ownerName\" was null.");
		}

		if(ownerName.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"ownerName\" was an empty string.");
		}

		mOwnerName = ownerName;
	}

	void setIsShiny(boolean isShiny)
	{
		mIsShiny = isShiny;
	}

	void setSpecies(Species species)
	{
		if(species == null)
		{
			throw new IllegalArgumentException("Passed value \"species\" was null.");
		}

		if(species.equals(Species.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"species\" was equal to " + species.toString() + ".");
		}

		mSpecies = species;
	}

	void setGender(Gender gender)
	{
		if(gender == null)
		{
			throw new IllegalArgumentException("Passed value \"gender\" was null.");
		}

		if(gender.equals(Gender.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"gender\" was equal to " + gender.toString() + ".");
		}

		mGender = gender;
	}

	void setPrimaryType(Type primaryType)
	{
		if(primaryType == null)
		{
			throw new IllegalArgumentException("Passed value \"primaryType\" was null.");
		}

		if(primaryType.equals(Type.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"primaryType\" was equal to " + primaryType.toString() + ".");
		}

		mPrimaryType = primaryType;
	}

	void setSecondaryType(Type secondaryType)
	{
		if(secondaryType == null)
		{
			throw new IllegalArgumentException("Passed value \"secondaryType\" was null.");
		}

		mSecondaryType = secondaryType;
	}

	void setMoveSet(MoveSet moveSet)
	{
		if(moveSet == null)
		{
			throw new IllegalArgumentException("Passed value \"moveSet\" was null.");
		}

		if(moveSet.equals(NullMoveSet.getNullMoveSet()))
		{
			throw new IllegalArgumentException("Passed value \"moveSet\" was equivalent to global NullMoveSet please use a clone instead.");
		}

		mMoveSet = moveSet;
	}

	void setAbility(IAbility iAbility)
	{
		if(iAbility == null)
		{
			throw new IllegalArgumentException("Passed value \"ability\" was null.");
		}

		if(iAbility.equals(NullAbility.getNullAbility()))
		{
			throw new IllegalArgumentException("Passed value \"ability\" was equal to the NullAbility ability.");
		}

		mAbility = iAbility;
	}

	void setStatus(StatusEffects statusEffect)
	{
		if(statusEffect == null)
		{
			throw new IllegalArgumentException("Passed value \"statusEffect\" was null.");
		}

		if(statusEffect.equals(StatusEffects.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"statusEffect\" was equal to " + statusEffect.toString() + ".");
		}

		mStatus = statusEffect;
	}

	void setStats(IStats stats)
	{
		if(stats == null)
		{
			throw new IllegalArgumentException("Passed value \"stats\" was null.");
		}

		if(stats.equals(NullStats.getNullStats()))
		{
			throw new IllegalArgumentException("Passed value \"stats\" was equal to the NullStats stats.");
		}

		mStats = stats;
	}

	void setIndexNumber(int indexNumber)
	{
		if(indexNumber < 0)
		{
			throw new IllegalArgumentException("Passed value \"indexNumber\" was below 0.");
		}

		mIndexNumber = indexNumber;
	}

	void setCatchRate(int catchRate)
	{
		if(catchRate < 1
				|| catchRate > 255)
		{
			throw new IllegalArgumentException("Passed value \"catchRate\" was below 1 or above 255.");
		}

		mCatchRate = catchRate;
	}

	/*
	 * PUBLIC GETS
	 */

	@Override
	public String getName()
	{
		return mName;
	}

	@Override
	public String getOwner()
	{
		return mOwnerName;
	}

	@Override
	public boolean isShiny()
	{
		return mIsShiny;
	}

	@Override
	public Species getSpecies()
	{
		return mSpecies;
	}

	@Override
	public Gender getGender()
	{
		return mGender;
	}

	@Override
	public Type getPrimaryType()
	{
		return mPrimaryType;
	}

	@Override
	public Type getSecondaryType()
	{
		return mSecondaryType;
	}

	@Override
	public MoveSet getMoveSet()
	{
		return mMoveSet;
	}

	@Override
	public IAbility getAbility()
	{
		return mAbility;
	}

	@Override
	public StatusEffects getStatus()
	{
		return mStatus;
	}

	@Override
	public IStats getStats()
	{
		return mStats;
	}

	@Override
	public int getIndexNumber()
	{
		return mIndexNumber;
	}

	@Override
	public int getCatchRate()
	{
		return mCatchRate;
	}

	/*
	 * PUBLIC METHODS
	 */

	@Override
	public void updateName(String name)
	{
		setName(name);
	}

	@Override
	public void updateStatus(StatusEffects status)
	{
		setStatus(status);
	}

	@Override
	public ArrayList<Type> getTypes()
	{
		ArrayList<Type> types = new ArrayList<Type>();

		if(!getPrimaryType().equals(Type.NotSet))
		{
			types.add(getPrimaryType());
		}
		if(!getSecondaryType().equals(Type.NotSet))
		{
			types.add(getSecondaryType());
		}

		return types;
	}

	@Override
	public void resetTempStats()
	{
		getStats().resetTempStats();
	}

	@Override
	public void applyDamage(int damage)
	{
		getStats().applyDamage(damage);
	}

	@Override
	public String applyHeal(int healAmount)
	{
		return getName() + getStats().applyHeal(healAmount);
	}

	@Override
	public void restore()
	{
		getStats().applyHeal(Integer.MAX_VALUE);
		getMoveSet().refreshAllMovePoints();
	}

	@Override
	public double getHitPointsPercent()
	{
		return getStats().getHitPointsPercent();
	}

	@Override
	public AnatureBuilder getClone()
	{
		return new AnatureBuilder().withName(getName()).withOwnerName(getOwner()).isShiny(isShiny()).withSpecies(getSpecies()).withGender(getGender())
				.withPrimaryType(getPrimaryType()).withSecondaryType(getSecondaryType()).withMoveSet(getMoveSet()).withAbility(getAbility())
				.withStatus(getStatus()).withStats(getStats()).withIndexNumber(getIndexNumber()).withCatchRate(getCatchRate());
	}

	@Override
	public Image getFrontSprite()
	{
		return new Image(getClass().getResource("/resources/images/anatures/" + getSpecies().toString() + "_Front.png").toExternalForm(), 1000.0, 1000.0, true,
				false);
	}

	@Override
	public Image getBackSprite()
	{
		return new Image(getClass().getResource("/resources/images/anatures/" + getSpecies().toString() + "_Back.png").toExternalForm(), 1000.0, 1000.0, true,
				false);
	}

	@Override
	public BattleAnimationType getMoveAnimationType(int moveIndex)
	{
		return getMoveSet().getMoveAnimationType(moveIndex);
	}

	@Override
	public String toString()
	{
		String testName = this.getPrimaryType().toString();

		if(this.getSecondaryType() != Type.NotSet)
		{
			testName = testName + ", " + this.getSecondaryType().toString();
		}

		return testName;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		if(getName().isEmpty())
		{
			throw new IllegalStateException("The \"name\" variable was never set during construction.");
		}

		if(getOwner().isEmpty())
		{
			throw new IllegalStateException("The \"ownerName\" variable was never set during construction.");
		}

		if(getSpecies().equals(Species.NotSet))
		{
			throw new IllegalStateException("The \"species\" variable was never set during construction.");
		}

		if(getGender().equals(Gender.NotSet))
		{
			throw new IllegalStateException("The \"gender\" variable was never set during construction.");
		}

		if(getPrimaryType().equals(Type.NotSet))
		{
			throw new IllegalStateException("The \"primaryType\" variable was never set during construction.");
		}

		if(getMoveSet().equals(NullMoveSet.getNullMoveSet()))
		{
			throw new IllegalStateException("The \"moveSet\" variable was never set during construction.");
		}

		if(getAbility().equals(NullAbility.getNullAbility()))
		{
			throw new IllegalStateException("The \"ability\" variable was never set during construction.");
		}

		if(getStatus().equals(StatusEffects.NotSet))
		{
			throw new IllegalStateException("The \"status\" variable was never set during construction.");
		}

		if(getStats().equals(NullStats.getNullStats()))
		{
			throw new IllegalStateException("The \"stats\" variable was never set during construction.");
		}

		if(getIndexNumber() == -1)
		{
			throw new IllegalStateException("The \"indexNumber\" variable was never set during construction.");
		}

		if(getCatchRate() == -1)
		{
			throw new IllegalStateException("The \"catchRate\" variable was never set during construction.");
		}

		return true;
	}
}