package strategy.producer.building.bakery.bread;

import strategy.item.flour.WheatFlour;
import strategy.item.food.baking.bread.WheatBread;
import strategy.item.mineral.Salt;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class WheatBreadBakery extends BreadBakery<WheatFlour, Salt, WheatBread> {

    public WheatBreadBakery(OneItemStorage<WheatFlour> firstSourceStorage, OneItemStorage<Salt> secondSourceStorage, OneItemStorage<WheatBread> destinationStorage, ProducerConfig producerConfig) {
        super(firstSourceStorage, secondSourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected WheatBread createNewItem(WheatFlour flour, Salt salt) {
        return new WheatBread();
    }
}
