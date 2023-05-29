package strategy.material.bar;

import strategy.material.mineral.ore.IronOre;

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
