package strategy.producer.building.smith.meele;

import strategy.item.bar.IronBar;
import strategy.item.weapon.meele.sword.IronSword;
import strategy.producer.ProducerConfig;
import strategy.storage.OneItemStorage;

public class IronSwordBlacksmith extends Blacksmith<IronBar, IronSword> {

    public IronSwordBlacksmith(OneItemStorage<IronBar> sourceStorage, OneItemStorage<IronSword> destinationStorage, ProducerConfig producerConfig) {
        super(sourceStorage, destinationStorage, producerConfig);
    }

    @Override
    protected IronSword createNewItem(IronBar material) {
        return new IronSword(material);
    }
}
