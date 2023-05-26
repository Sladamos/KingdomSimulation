package strategy.kingdom.building.producer.mine.advanced;

import strategy.kingdom.building.Building;
import strategy.kingdom.building.producer.Producer;
import strategy.kingdom.building.producer.mine.basic.IronMiner;
import strategy.kingdom.building.producer.mine.basic.RubyMiner;
import strategy.kingdom.material.mineral.gem.Ruby;
import strategy.kingdom.material.mineral.ore.IronOre;

public class SarraxMiner implements Producer {

    private final IronMiner ironMiner;

    private final RubyMiner rubyMiner;

    public SarraxMiner() {
        this.ironMiner = new IronMiner(15);
        this.rubyMiner = new RubyMiner(10);
    }

    @Override
    public void run() {
        Thread thread = new Thread(ironMiner);
        thread.start();
        rubyMiner.run();
    }

    @Override
    public synchronized boolean isDestroyed() {
        return ironMiner.isDestroyed() && rubyMiner.isDestroyed();
    }

    // @throws - BuildingDestroyedException if two mines are destroyed
    @Override
    public synchronized void dealDamage(int damage) {
        if(rubyMiner.isDestroyed() || ironMiner.isDestroyed()) {
            dealDamageIfOneMineIsDestroyed(damage);
        } else {
            dealDamageForBothMines(damage);
        }
    }

    @Override
    public int getDurability() {
        return ironMiner.getDurability() + rubyMiner.getDurability();
    }

    private void dealDamageIfOneMineIsDestroyed(int damage) {
        if(ironMiner.isDestroyed()) {
            rubyMiner.dealDamage(damage);
        } else if (rubyMiner.isDestroyed()) {
            ironMiner.dealDamage(damage);
        }
    }

    private void dealDamageForBothMines(int damage) {

        int damageForIronMiner = ironMiner.getDurability() < damage / 2 ? ironMiner.getDurability() : damage / 2;
        int damageForRubyMiner = rubyMiner.getDurability() < damage / 2 ? rubyMiner.getDurability() : damage / 2;

        ironMiner.dealDamage(damageForIronMiner);
        rubyMiner.dealDamage(damageForRubyMiner);
    }

    public IronOre getIronOre() {
        return ironMiner.getOre();
    }

    public Ruby getRuby() {
        return rubyMiner.getOre();
    }
}
