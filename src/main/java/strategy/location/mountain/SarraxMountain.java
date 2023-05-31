package strategy.location.mountain;

import strategy.producer.building.miner.advanced.SarraxMiner;
import strategy.producer.building.miner.basic.*;
import strategy.material.mineral.Salt;
import strategy.material.mineral.gem.Ruby;
import strategy.material.mineral.gem.Sapphire;
import strategy.material.mineral.ore.IronOre;

public class SarraxMountain implements Mountain, Runnable {

    private final Miner<Sapphire> sapphireMiner;

    private final Miner<Salt> saltMiner;

    private final SarraxMiner sarraxMiner;

    public SarraxMountain() {
        saltMiner = new SaltMiner(4);
        sapphireMiner = new SapphireMiner(4);
        sarraxMiner = new SarraxMiner();
    }

    @Override
    public void run() {
        Thread thread = new Thread(saltMiner);
        thread.run();
        thread = new Thread(sarraxMiner);
        thread.run();
        thread = new Thread(sapphireMiner);
        thread.run();
    }

    public synchronized Salt getSalt() {
        return saltMiner.getMineral();
    }

    public synchronized Sapphire getSapphire() {
        return sapphireMiner.getMineral();
    }

    public synchronized Ruby getRuby() {
        return sarraxMiner.getRuby();
    }

    public synchronized IronOre getIronOre() {
        return sarraxMiner.getIronOre();
    }
}
