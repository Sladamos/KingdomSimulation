package strategy.item.military.infantry.warrior;

import strategy.item.military.infantry.HumanInfantryUnit;

public class Warrior extends HumanInfantryUnit {

    public Warrior(WarriorConfig warriorConfig) {
        super(warriorConfig);
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
