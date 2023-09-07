package strategy.location.mountain;

import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Sapphire;
import strategy.producer.building.miner.advanced.SarraxMiner;
import strategy.producer.building.miner.basic.Miner;
import strategy.producer.building.miner.basic.SaltMiner;
import strategy.producer.building.miner.basic.SapphireMiner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxMountain implements Mountain, Runnable {

    private final Miner<Sapphire> sapphireMiner;

    private final Miner<Salt> saltMiner;

    private final SarraxMiner sarraxMiner;

    private final ExecutorService executorService;

    public SarraxMountain(MountainStorageManager mountainStorageManager) {
        saltMiner = new SaltMiner(mountainStorageManager.getSaltStorage(), null);
        sapphireMiner = new SapphireMiner(mountainStorageManager.getSapphireStorage(), null);
        sarraxMiner = new SarraxMiner(mountainStorageManager.getIronOreStorage(), mountainStorageManager.getRubyStorage());
        executorService = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        executorService.execute(saltMiner);
        executorService.execute(sarraxMiner);
        executorService.execute(sapphireMiner);
    }

    @Override
    public void terminate() {
        saltMiner.terminate();
        sarraxMiner.terminate();
        sapphireMiner.terminate();
        executorService.shutdownNow();
    }
}
