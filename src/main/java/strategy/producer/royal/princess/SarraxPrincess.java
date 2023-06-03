package strategy.producer.royal.princess;

import strategy.producer.TwoToOneProducer;
import strategy.product.present.Present;
import strategy.product.statistic.Happiness;

import java.util.function.Supplier;

public class SarraxPrincess<T extends Present, S extends Present> extends TwoToOneProducer<T, S, Happiness> implements Princess {

    private static final int PRINCESS_PRODUCING_SPEED = 2;

    private static final int PRINCESS_DURABILITY = Integer.MAX_VALUE;

    public SarraxPrincess(Supplier<T> firstProducer, Supplier<S> secondProducer) {
        super(firstProducer, secondProducer, 0, PRINCESS_PRODUCING_SPEED, PRINCESS_DURABILITY);
    }

    public synchronized Happiness getHappiness() {
        return getItem();
    }

    @Override
    protected Happiness produceNewItem(T material, S secondMaterial) {
        return new Happiness();
    }

    @Override
    protected Happiness produceNewItem() {
        return new Happiness();
    }

    @Override
    protected int getProducingTime() {
        return 45000;
    }
}
