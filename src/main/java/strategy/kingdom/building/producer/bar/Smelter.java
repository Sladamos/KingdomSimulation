package strategy.kingdom.building.producer.bar;

import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.material.bar.Bar;
import strategy.kingdom.material.mineral.ore.Ore;

public abstract class Smelter <T extends Ore, S extends Bar> implements Producer {

    @Override
    public void run() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void dealDamage(int damage) {

    }
}
