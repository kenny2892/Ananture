package test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import application.TypeAdvantage;
import application.anatures.Anature;
import application.anatures.AnatureBuilder;
import application.anatures.MoveSet;
import application.anatures.abillities.Spiky;
import application.enums.AttackEffectiveness;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;

@DisplayName("Type Advantage Tests")
class TypeAdvantageTests
{
	private Anature baseAnature;
	
	private Anature setAnatureTypes(Type[] types)
	{
		Type primaryType = types[0];
		Type secondaryType = Type.NotSet;
		
		if(types.length == 2)
		{
			secondaryType = types[1];
		}
		
		for(int index = 0; index < types.length; index++)
		{
			
		}
		return new AnatureBuilder().setName(baseAnature.getName())
				.setOwnerName(baseAnature.getOwner())
				.setIsShiny(baseAnature.isShiny())
				.setSpecies(baseAnature.getSpecies())
				.setGender(baseAnature.getGender())
				.setPrimaryType(primaryType)
				.setSecondaryType(secondaryType)
				.setMoveSet(baseAnature.getMoveSet())
				.setAbility(baseAnature.getAbility())
				.setStatus(baseAnature.getStatus())
				.setIndexNumber(baseAnature.getIndexNumber())
				.setLevel(baseAnature.getLevel())
				.setCurrentExperiencePoints(baseAnature.getCurrentExpereincePoints())
				.setTotalHitPoints(baseAnature.getTotalHitPoints())
				.setCurrentHitPoints(baseAnature.getCurrentHitPoints())
				.setAttack(baseAnature.getAttack())
				.setDefense(baseAnature.getDefense())
				.setSpecialAttack(baseAnature.getSpecialAttack())
				.setSpecialDefense(baseAnature.getSpecialDefense())
				.setSpeed(baseAnature.getSpeed())
				.setAccuracy(baseAnature.getAccuracy())
				.create();
	}

	@BeforeAll
	void generateBaseAnature()
	{
		String name = "<Test Creature>";
		String ownerName = "<Test Owner>";
		boolean isShiny = false;
		Species species = Species.Null;
		Gender gender = Gender.Trans;
		Type primaryType = null;
		Type secondaryType = null;
		MoveSet moveSet = new MoveSet(null, null, null, null);
		IAbility iAbility = new Spiky();
		int indexNumber = 10;
		int level = 10;
		int currentExperiencePoints = 10;
		int totalHitPoints = 10;
		int currentHitPoints = 10;
		int attack = 10;
		int defense = 10;
		int specialAttack = 10;
		int specialDefense = 10;
		int speed = 10;
		int accuracy = 10;
		StatusEffects statusEffect = StatusEffects.None;

		baseAnature = new AnatureBuilder().setName(name)
				.setOwnerName(ownerName)
				.setIsShiny(isShiny)
				.setSpecies(species)
				.setGender(gender)
				.setPrimaryType(primaryType)
				.setSecondaryType(secondaryType)
				.setMoveSet(moveSet)
				.setAbility(iAbility)
				.setStatus(statusEffect)
				.setIndexNumber(indexNumber)
				.setLevel(level)
				.setCurrentExperiencePoints(currentExperiencePoints)
				.setTotalHitPoints(totalHitPoints)
				.setCurrentHitPoints(currentHitPoints)
				.setAttack(attack)
				.setDefense(defense)
				.setSpecialAttack(specialAttack)
				.setSpecialDefense(specialDefense)
				.setSpeed(speed)
				.setAccuracy(accuracy)
				.create();
	}

	@TestTemplate
	@ExtendWith(AdvantageValueTestTempalte.class)
	void testEquals(TypeEffectivenessTestCase testCase)
	{
		Assert.assertEquals(TypeAdvantage.anatureEffectiveness(testCase.attacker, testCase.defender), testCase.expectedEffectiveness);
	}

	public class AdvantageValueTestTempalte implements TestTemplateInvocationContextProvider
	{
		private TypeEffectivenessTestCase createTestCase(Type[] sourceTypes, Type[] targetTypes, AttackEffectiveness expectedResult)
		{
			Anature sourceAnature = setAnatureTypes(sourceTypes);
			Anature targetAnature = setAnatureTypes(targetTypes);
			return new TypeEffectivenessTestCase(sourceAnature, targetAnature, expectedResult);
		}

		@Override
		public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext arg0)
		{
			return Stream.of(addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Rock }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Normal }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Water }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Grass }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Rock }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Ice }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Bug }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Steel }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fire }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Normal }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Flying }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Poison }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Rock }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Ice }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Bug }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Dark }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Steel }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fighting }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Fire }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Water }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Ground }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Rock }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Steel }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Water }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Grass }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Electric }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Rock }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Bug }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Flying }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Water }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Flying }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Poison }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Ground }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Rock }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Bug }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Grass }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Grass }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Poison }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Ground }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Rock }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Poison }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Water }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Flying }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Electric }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Ground }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Steel }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Fire }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Flying }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Poison }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Electric }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Rock }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Bug }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Steel }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ground }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Poison }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Dark }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Psychic }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Fire }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Flying }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Ground }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Ice }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Bug }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Rock }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Water }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Flying }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Grass }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Ground }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Ice }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ice }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Flying }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Grass }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Poison }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Dark }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Bug }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Dragon }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Normal }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Dark }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Steel }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Ghost }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Dark }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Steel }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Dark }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Water }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Poison }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Electric }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Rock }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Ice }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Dark }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Steel }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Normal }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Fire }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Fighting }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Water }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Flying }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Grass }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Poison }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Electric }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Ground }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Psychic }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Rock }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Ice }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Bug }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Dragon }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Ghost }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Dark }, AttackEffectiveness.SuperEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Steel }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
			{ Type.Fairy }, new Type[]
			{ Type.Fairy }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Water, Type.Ground }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Ice, Type.Ground }, AttackEffectiveness.NoEffect)), addCase(createTestCase(new Type[]
			{ Type.Electric }, new Type[]
			{ Type.Electric, Type.Ground }, AttackEffectiveness.NoEffect)),

					addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Water, Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Ice, Type.Grass }, AttackEffectiveness.NotEffective)), addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Electric, Type.Grass }, AttackEffectiveness.NotEffective)),

					addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Water, Type.Fire }, AttackEffectiveness.Normal)), addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Normal, Type.Fire }, AttackEffectiveness.Normal)),

					addCase(createTestCase(new Type[]
					{ Type.Electric }, new Type[]
					{ Type.Water, Type.Flying }, AttackEffectiveness.SuperEffective)));
		}

		@Override
		public boolean supportsTestTemplate(ExtensionContext arg0)
		{
			return true;
		}

		private TestTemplateInvocationContext addCase(TypeEffectivenessTestCase testCase)
		{
			return new TestTemplateInvocationContext()
			{
				@Override
				public String getDisplayName(int invocationIndex)
				{
					return testCase.testCaseToString();
				}

				@Override
				public List<Extension> getAdditionalExtensions()
				{
					return Collections.singletonList(new ParameterResolver()
					{
						@Override
						public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
						{
							return parameterContext.getParameter()
									.getType()
									.equals(TypeEffectivenessTestCase.class);
						}

						@Override
						public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
						{
							return testCase;
						}
					});
				}
			};
		}

	}
}
