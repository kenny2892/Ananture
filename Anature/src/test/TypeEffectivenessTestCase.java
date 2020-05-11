package test;

import application.enums.AttackEffectiveness;
import application.interfaces.IAnature;

class TypeEffectivenessTestCase
{
	IAnature attacker;
	IAnature defender;
	AttackEffectiveness expectedEffectiveness;

	public TypeEffectivenessTestCase(IAnature attacker, IAnature defender, AttackEffectiveness expectedEffectiveness)
	{
		this.attacker = attacker;
		this.defender = defender;
		this.expectedEffectiveness = expectedEffectiveness;
	}

	String testCaseToString()
	{
		return "EffectivenessValue_" + testCaseTypesToString(attacker) + "To" + testCaseTypesToString(defender) + "_Returns" + expectedEffectiveness.toString();
	}

	private String testCaseTypesToString(IAnature anatureBase)
	{
		String types = "";
		if(anatureBase.getPrimaryType() != null)
		{
			types = types.concat(anatureBase.getPrimaryType()
					.toString());
		}
		if(anatureBase.getSecondaryType() != null)
		{
			types = types.concat(anatureBase.getSecondaryType()
					.toString());
		}
		return types;
	}
}