package application.pools;

import java.util.HashMap;

import application.anatures.moves.MoveBuilder;
import application.anatures.moves.abstracts.*;
import application.anatures.moves.uniques.*;
import application.enums.MoveIds;
import application.enums.Type;
import application.interfaces.IMove;

public class MovePool
{
	private static HashMap<MoveIds, IMove> mMoves;

	public static IMove getMove(MoveIds moveId)
	{
		if(mMoves == null)
		{
			generateMoves();
		}

		return mMoves.get(moveId);
	}

	private static void generateMoves()
	{
		mMoves = new HashMap<MoveIds, IMove>();
		
		generateNormalMoves();
		generateFightingMoves();
		generateFireMoves();
		generateGroundMoves();
		generatePoisonMoves();
		generateElectricMoves();
		generatePsychicMoves();
		generateRockMoves();
		generateWaterMoves();
		generateFlyingMoves();
		generateGrassMoves();
	}

	// @formatter:off
	private static void generateNormalMoves()
	{
		mMoves.put(MoveIds.Tackle,new MoveBuilder<JustDamageDealing>(MoveIds.Tackle)
				.withName("Tackle")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(60)
				.withAccuracy(85)
				.create()
		);

		mMoves.put(MoveIds.Grumble, new MoveBuilder<DecreaseStats>(MoveIds.Grumble)
				.withName("Grumble")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Smash,new MoveBuilder<JustDamageDealing>(MoveIds.Smash)
				.withName("Smash")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(25)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Scary_Face,new MoveBuilder<DecreaseStats>(MoveIds.Scary_Face)
				.withName("Scary Face")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Clop,new MoveBuilder<JustDamageDealing>(MoveIds.Clop)
				.withName("Clop")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(20)
				.withMovePower(65)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Antler_Shot,new MoveBuilder<AntlerShot>(MoveIds.Antler_Shot)
				.withName("Antler Shot")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(15)
				.withMovePower(75)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Holler,new MoveBuilder<DecreaseStats>(MoveIds.Holler)
				.withName("Holler")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(0)
				.withAccuracy(70)
				.create()
		);

		mMoves.put(MoveIds.Healing_Winds,new MoveBuilder<HealingWinds>(MoveIds.Healing_Winds)
				.withName("Healing Winds")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(0)
				.withAccuracy(0)
				.create()
		);

		mMoves.put(MoveIds.Coil,new MoveBuilder<Coil>(MoveIds.Coil)
				.withName("Coil")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(15)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Pounce,new MoveBuilder<JustDamageDealing>(MoveIds.Pounce)
				.withName("Pounce")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(65)
				.withAccuracy(90)
				.create()
		);

		mMoves.put(MoveIds.Sharpen_Up,new MoveBuilder<JustDamageDealing>(MoveIds.Sharpen_Up)
				.withName("Sharpen Up")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Body_Slam,new MoveBuilder<JustDamageDealing>(MoveIds.Body_Slam)
				.withName("Body Slam")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(10)
				.withMovePower(90)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Skip_Turn, new MoveBuilder<SkipTurn>(MoveIds.Skip_Turn) // TODO REMOVE
				.withName("Skip Turn")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(0)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		// TODO Flail will eventually run out of Move Points... almost impossible but there might be a work around
		mMoves.put(MoveIds.Flail,new MoveBuilder<Flail>(MoveIds.Flail)
				.withName("Flail")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(Integer.MAX_VALUE)
				.withMovePower(50)
				.withAccuracy(100)
				.create()
		);
	}

	private static void generateFightingMoves()
	{
		mMoves.put(MoveIds.Double_Punch, new MoveBuilder<DoublePunch>(MoveIds.Double_Punch)
				.withName("Double Punch")
				.withType(Type.Fighting)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(35)
				.withAccuracy(75)
				.create()
		);
	}

	private static void generateFireMoves()
	{
		mMoves.put(MoveIds.Flamethrower, new MoveBuilder<DamageAndStatus>(MoveIds.Flamethrower)
				.withName("Flamethrower")
				.withType(Type.Fire)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(80)
				.withAccuracy(75)
				.create()
		);

		mMoves.put(MoveIds.Cinder,new MoveBuilder<DamageAndStatus>(MoveIds.Cinder)
				.withName("Cinder")
				.withType(Type.Fire)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(40)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Fire_Torture,new MoveBuilder<DamageAndStatus>(MoveIds.Fire_Torture)
				.withName("Fire Torture")
				.withType(Type.Fire)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(90)
				.withAccuracy(80)
				.create()
		);

		mMoves.put(MoveIds.Fire_Cell,new MoveBuilder<DamageAndStatus>(MoveIds.Fire_Cell)
				.withName("Fire Cell")
				.withType(Type.Fire)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(10)
				.withMovePower(100)
				.withAccuracy(90)
				.create()
		);

		mMoves.put(MoveIds.Flame_Bout,new MoveBuilder<DamageAndStatus>(MoveIds.Flame_Bout)
				.withName("Flame Bout")
				.withType(Type.Fire)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(120)
				.withAccuracy(80)
				.create()
		);
	}

	private static void generateGroundMoves()
	{
		mMoves.put(MoveIds.Pocket_Sand, new MoveBuilder<DecreaseStats>(MoveIds.Pocket_Sand)
				.withName("Pocket Sand")
				.withType(Type.Ground)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Knock_Down,new MoveBuilder<DamageAndDecreaseStats>(MoveIds.Knock_Down)
				.withName("Knock Down")
				.withType(Type.Ground)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(60)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Tremor,new MoveBuilder<JustDamageDealing>(MoveIds.Tremor)
				.withName("Tremor")
				.withType(Type.Ground)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(10)
				.withMovePower(100)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Sludge_Missile,new MoveBuilder<DamageAndDecreaseStats>(MoveIds.Sludge_Missile)
				.withName("Sludge Missile")
				.withType(Type.Ground)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(65)
				.withAccuracy(85)
				.create()
		);

		mMoves.put(MoveIds.Sludge_Slap,new MoveBuilder<DamageAndDecreaseStats>(MoveIds.Sludge_Slap)
				.withName("Sludge Slap")
				.withType(Type.Ground)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(20)
				.withAccuracy(100)
				.create()
		);
	}

	private static void generatePoisonMoves()
	{
		mMoves.put(MoveIds.Smog_Wave,new MoveBuilder<SmogWave>(MoveIds.Smog_Wave)
				.withName("Smog Wave")
				.withType(Type.Poison)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);
	}

	private static void generateElectricMoves()
	{
		mMoves.put(MoveIds.Electro_Sonic,new MoveBuilder<DamageAndStatus>(MoveIds.Electro_Sonic)
				.withName("Electro Sonic")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(40)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Stormy_Breeze,new MoveBuilder<JustDamageDealing>(MoveIds.Stormy_Breeze)
				.withName("Stormy Breeze")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(20)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Light_Missle,new MoveBuilder<DamageAndDecreaseStats>(MoveIds.Light_Missle)
				.withName("Light Missile")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(10)
				.withMovePower(80)
				.withAccuracy(80)
				.create()
		);

		mMoves.put(MoveIds.Lightning,new MoveBuilder<DamageAndStatus>(MoveIds.Lightning)
				.withName("Lightning")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(5)
				.withMovePower(110)
				.withAccuracy(80)
				.create()
		);

		mMoves.put(MoveIds.Electrocution,new MoveBuilder<Electrocution>(MoveIds.Electrocution)
				.withName("Electrocution")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(5)
				.withMovePower(120)
				.withAccuracy(75)
				.create()
		);

		mMoves.put(MoveIds.Zap,new MoveBuilder<JustDamageDealing>(MoveIds.Zap)
				.withName("Zap")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(45)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Shock_Blast,new MoveBuilder<JustDamageDealing>(MoveIds.Shock_Blast)
				.withName("Shock Blast")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(65)
				.withAccuracy(95)
				.create()
		);

		mMoves.put(MoveIds.Wire_Smack,new MoveBuilder<JustDamageDealing>(MoveIds.Wire_Smack)
				.withName("Wire Smack")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(75)
				.withAccuracy(95)
				.create()
		);

		mMoves.put(MoveIds.Upgrade,new MoveBuilder<JustDamageDealing>(MoveIds.Upgrade)
				.withName("Upgrade")
				.withType(Type.Electric)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Factory_Reset,new MoveBuilder<JustDamageDealing>(MoveIds.Factory_Reset)
				.withName("Factory Reset")
				.withType(Type.Electric)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Thunder_Blast,new MoveBuilder<JustDamageDealing>(MoveIds.Thunder_Blast)
				.withName("Thunder Blast")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(95)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Wired_Mess,new MoveBuilder<JustDamageDealing>(MoveIds.Wired_Mess)
				.withName("Wired Mess")
				.withType(Type.Electric)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Voltage_Overload,new MoveBuilder<JustDamageDealing>(MoveIds.Voltage_Overload)
				.withName("Voltage Overload")
				.withType(Type.Electric)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(110)
				.withAccuracy(85)
				.create()
		);
	}

	private static void generatePsychicMoves()
	{
		mMoves.put(MoveIds.Magical_Spice,new MoveBuilder<JustDamageDealing>(MoveIds.Magical_Spice)
				.withName("Magical Spice")
				.withType(Type.Psychic)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(10)
				.withMovePower(70)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Nimbleness, new MoveBuilder<IncreaseStats>(MoveIds.Nimbleness)
				.withName("Nimbleness")
				.withType(Type.Psychic)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(30)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Miss_Direction, new MoveBuilder<JustDamageDealing>(MoveIds.Miss_Direction)
				.withName("Miss Direction")
				.withType(Type.Psychic)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(50)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Zen, new MoveBuilder<IncreaseStats>(MoveIds.Zen)
				.withName("Zen")
				.withType(Type.Psychic)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(20)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Slumber, new MoveBuilder<JustStatus>(MoveIds.Slumber)
				.withName("Slumber")
				.withType(Type.Psychic)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(20)
				.withMovePower(0)
				.withAccuracy(60)
				.create()
		);

		mMoves.put(MoveIds.Channel, new MoveBuilder<JustDamageDealing>(MoveIds.Channel)
				.withName("Channel")
				.withType(Type.Psychic)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(90)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Restore, new MoveBuilder<Restore>(MoveIds.Restore)
				.withName("Restore")
				.withType(Type.Psychic)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Focused_Heatbutt, new MoveBuilder<JustDamageDealing>(MoveIds.Focused_Heatbutt)
				.withName("Focused Heatbutt")
				.withType(Type.Psychic)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(15)
				.withMovePower(80)
				.withAccuracy(90)
				.create()
		);

		mMoves.put(MoveIds.Mystic_Power, new MoveBuilder<IncreaseStats>(MoveIds.Mystic_Power)
				.withName("Mystic Power")
				.withType(Type.Psychic)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(20)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);
	}

	private static void generateRockMoves()
	{
		mMoves.put(MoveIds.Forgotten_Awakening,new MoveBuilder<DamageAndIncreaseStats>(MoveIds.Forgotten_Awakening)
				.withName("Forgotten Awakening")
				.withType(Type.Rock)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(5)
				.withMovePower(60)
				.withAccuracy(100)
				.create()
		);
	}

	private static void generateWaterMoves()
	{
		mMoves.put(MoveIds.Water_Toss,new MoveBuilder<DamageAndDecreaseStats>(MoveIds.Water_Toss)
				.withName("Water Toss")
				.withType(Type.Water)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(30)
				.withMovePower(40)
				.withAccuracy(95)
				.create()
		);
	
		mMoves.put(MoveIds.Hose_Down,new MoveBuilder<DecreaseStats>(MoveIds.Hose_Down)
				.withName("Hose Down")
				.withType(Type.Water)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(0)
				.withAccuracy(90)
				.create()
		);

		mMoves.put(MoveIds.Water_Fang,new MoveBuilder<JustDamageDealing>(MoveIds.Water_Fang)
				.withName("Water Fang")
				.withType(Type.Water)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(15)
				.withMovePower(55)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Water_Blast,new MoveBuilder<JustDamageDealing>(MoveIds.Water_Blast)
				.withName("Water Blast")
				.withType(Type.Water)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(75)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Flood,new MoveBuilder<JustDamageDealing>(MoveIds.Flood)
				.withName("Flood")
				.withType(Type.Water)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(120)
				.withAccuracy(75)
				.create()
		);
	}

	private static void generateFlyingMoves()
	{
		mMoves.put(MoveIds.Wing_Bash,new MoveBuilder<JustDamageDealing>(MoveIds.Wing_Bash)
				.withName("Wing Bash")
				.withType(Type.Flying)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(80)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Tornado,new MoveBuilder<JustDamageDealing>(MoveIds.Tornado)
				.withName("Tornado")
				.withType(Type.Flying)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(110)
				.withAccuracy(85)
				.create()
		);
	}

	private static void generateGrassMoves()
	{
		mMoves.put(MoveIds.Leaf_Storm,new MoveBuilder<JustDamageDealing>(MoveIds.Leaf_Storm)
				.withName("Leaf Storm")
				.withType(Type.Grass)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(15)
				.withMovePower(90)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Leaf_Sword,new MoveBuilder<JustDamageDealing>(MoveIds.Leaf_Sword)
				.withName("Leaf Sword")
				.withType(Type.Grass)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(55)
				.withAccuracy(95)
				.create()
		);

		mMoves.put(MoveIds.Grass_Whip,new MoveBuilder<JustDamageDealing>(MoveIds.Grass_Whip)
				.withName("Grass Whip")
				.withType(Type.Grass)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(45)
				.withAccuracy(100)
				.create()
		);

		mMoves.put(MoveIds.Slow_Spore,new MoveBuilder<DecreaseStats>(MoveIds.Slow_Spore)
				.withName("Slow Spore")
				.withType(Type.Grass)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(40)
				.withMovePower(0)
				.withAccuracy(100)
				.create()
		);
	}
	// @formatter:on
}
