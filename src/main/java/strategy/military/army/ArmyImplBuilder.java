package strategy.military.army;

public class ArmyImplBuilder {
	private int damageModifier;

	public ArmyImplBuilder withDamageModifier(int damageModifier) {
		this.damageModifier = damageModifier;
		return this;
	}

	public ArmyImplBuilder withoutDamageModifier() {
		this.damageModifier = 1;
		return this;
	}

	public ArmyImpl createNewArmy() {
		return new ArmyImpl(damageModifier);
	}
}