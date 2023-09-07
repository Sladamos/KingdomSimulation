package strategy.location.mountain;

import strategy.producer.building.miner.advanced.SarraxMiner;
import strategy.producer.building.miner.basic.*;
import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Ruby;
import strategy.item.mineral.gem.Sapphire;
import strategy.item.mineral.ore.IronOre;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxMountain implements Mountain, Runnable {

    private final Miner<Sapphire> sapphireMiner;

    private final Miner<Salt> saltMiner;

    private final SarraxMiner sarraxMiner;

    private final ExecutorService executorService;

    public SarraxMountain() {
        saltMiner = new SaltMiner(0);
        sapphireMiner = new SapphireMiner(0);
        sarraxMiner = new SarraxMiner();
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        executorService.execute(saltMiner);
        executorService.execute(sarraxMiner);
        executorService.execute(sapphireMiner);
    }

    public Salt getSalt() {
        return saltMiner.getMineral();
    }

    public Sapphire getSapphire() {
        return sapphireMiner.getMineral();
    }

    public Ruby getRuby() {
        return sarraxMiner.getRuby();
    }

    public IronOre getIronOre() {
        return sarraxMiner.getIronOre();
    }

    @Override
    public void terminate() {
        saltMiner.terminate();
        sarraxMiner.terminate();
        sapphireMiner.terminate();
        executorService.shutdownNow();
    }
}
