package strategy.military.infantry;

public class Warrior extends HumanInfantryUnit {

    public Warrior(int damage, int defense) {
        super(damage, defense);
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
