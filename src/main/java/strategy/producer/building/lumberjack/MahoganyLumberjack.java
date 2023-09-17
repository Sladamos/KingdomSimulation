package strategy.producer.building.lumberjack;

import strategy.item.wood.Mahogany;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class MahoganyLumberjack extends Lumberjack<Mahogany> {

    public MahoganyLumberjack(OneItemStorage<Mahogany> destinationStorage, ProducerConfig producerConfig) {
        super(destinationStorage, producerConfig);
    }

    @Override
    protected Mahogany createNewItem() {
        return new Mahogany();
    }
}
