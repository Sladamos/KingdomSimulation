package strategy.location.mountain;

import strategy.item.mineral.Salt;
import strategy.item.mineral.gem.Sapphire;
import strategy.producer.building.miner.advanced.SarraxMiner;
import strategy.producer.building.miner.basic.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxMountain implements Mountain, Runnable {

    private final Miner<Sapphire> sapphireMiner;

    private final Miner<Salt> saltMiner;

    private final SarraxMiner sarraxMiner;

    private final ExecutorService executorService;

    public SarraxMountain(MountainStorageManager mountainStorageManager, MountainConfig mountainConfig) {
        saltMiner = new SaltMiner(mountainStorageManager.getSaltStorage(), mountainConfig.getSaltMinerConfig());
        sapphireMiner = new SapphireMiner(mountainStorageManager.getSapphireStorage(), mountainConfig.getSapphireMinerConfig());
        sarraxMiner = createSarraxMiner(mountainStorageManager, mountainConfig);
        executorService = Executors.newFixedThreadPool(3);
    }

    private SarraxMiner createSarraxMiner(MountainStorageManager mountainStorageManager, MountainConfig mountainConfig) {
        IronMiner ironMiner = new IronMiner(mountainStorageManager.getIronOreStorage(), mountainConfig.getIronOreMinerConfig());
        RubyMiner rubyMiner = new RubyMiner(mountainStorageManager.getRubyStorage(), mountainConfig.getRubyMinerConfig());
        return new SarraxMiner(ironMiner, rubyMiner);
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
