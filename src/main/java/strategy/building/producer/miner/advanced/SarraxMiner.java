package strategy.building.producer.miner.advanced;

import strategy.building.producer.miner.basic.IronMiner;
import strategy.building.producer.Producer;
import strategy.building.producer.miner.basic.RubyMiner;
import strategy.material.mineral.gem.Ruby;
import strategy.material.mineral.ore.IronOre;

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
    public synchronized int getDurability() {
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

    public synchronized IronOre getIronOre() {
        return ironMiner.getMaterial();
    }

    public synchronized Ruby getRuby() {
        return rubyMiner.getMaterial();
    }
}
