package strategy.producer.building.smith.meele;

import strategy.item.bar.IronBar;
import strategy.item.weapon.meele.sword.IronSword;

import java.util.function.Supplier;

public class IronSwordBlacksmith extends Blacksmith<IronBar, IronSword> {

    public IronSwordBlacksmith(Supplier<IronBar> materialProducer, int defaultStorageSize, double forgingSpeed, int durability) {
        super(materialProducer, defaultStorageSize, forgingSpeed, durability);
    }

    @Override
    protected IronSword createNewItem(IronBar material) {
        return new IronSword(material);
    }
}
