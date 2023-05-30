package strategy.building.producer.mill;

import strategy.material.plant.Wheat;
import strategy.product.flour.WheatFlour;

import java.util.function.Supplier;

public class WheatMill extends Mill<Wheat, WheatFlour> {

    private static final int WHEAT_MILL_DURABILITY = 190;

    private static final int WHEAT_MILL_MILLING_SPEED = 7;

    public WheatMill(Supplier<Wheat> plantProducer, int defaultStorageSize) {
        super(plantProducer, defaultStorageSize, WHEAT_MILL_MILLING_SPEED,
                WHEAT_MILL_DURABILITY);
    }

    @Override
    protected WheatFlour createNewFlour(Wheat plant) {
        return new WheatFlour();
    }

    @Override
    protected WheatFlour createNewFlour() {
        return new WheatFlour();
    }

    @Override
    protected int getMillingTime() {
        return 32000;
    }
}
