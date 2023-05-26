package strategy.kingdom.building.producer.bar;

import strategy.kingdom.material.bar.IronBar;
import strategy.kingdom.material.mineral.ore.IronOre;

import java.util.function.Supplier;

public class IronBarSmelter extends Smelter<IronOre, IronBar> {

    private static final int IRON_SMELTER_DURABILITY = 200;

    private static final int IRON_SMELTER_SMELTING_SPEED = 4;


    public IronBarSmelter(Supplier<IronOre> oresProducer, int defaultStorageSize) {
        super(oresProducer, defaultStorageSize, IRON_SMELTER_SMELTING_SPEED, IRON_SMELTER_DURABILITY);
    }

    @Override
    protected IronBar createNewBar(IronOre ore) {
        return new IronBar(ore);
    }

    @Override
    protected IronBar createNewBar() {
        return new IronBar();
    }
}
