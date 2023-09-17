package strategy.item.bar;

import strategy.item.mineral.ore.IronOre;

public class IronBar implements Bar {

    public IronBar() {

    }

    public IronBar(IronOre ore) {
    }

    @Override
    public String toString() {
        return "Iron bar";
    }
}
