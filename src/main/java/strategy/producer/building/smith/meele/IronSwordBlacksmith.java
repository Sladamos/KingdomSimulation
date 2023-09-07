package strategy.producer.building.smith.meele;

import strategy.item.bar.IronBar;
import strategy.item.weapon.meele.sword.IronSword;

import java.util.function.Supplier;

public class IronSwordBlacksmith extends Blacksmith<IronBar, IronSword> {

    private static final int IRON_SWORD_BLACKSMITH_DURABILITY = 180;

    private static final int IRON_SWORD_BLACKSMITH_FORGING_SPEED = 3;

    public IronSwordBlacksmith(Supplier<IronBar> materialProducer, int defaultStorageSize) {
        super(materialProducer, defaultStorageSize, IRON_SWORD_BLACKSMITH_FORGING_SPEED,
                IRON_SWORD_BLACKSMITH_DURABILITY);
    }

    @Override
    protected IronSword createNewItem(IronBar material) {
        return new IronSword(material);
    }

    @Override
    protected IronSword produceNewItem() {
        return new IronSword();
    }
}
