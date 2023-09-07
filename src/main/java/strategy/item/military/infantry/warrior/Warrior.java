package strategy.item.military.infantry.warrior;

import strategy.item.military.infantry.HumanInfantryUnit;

public class Warrior extends HumanInfantryUnit {

    public Warrior(int damage, int defense) {
        super(damage, defense);
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
