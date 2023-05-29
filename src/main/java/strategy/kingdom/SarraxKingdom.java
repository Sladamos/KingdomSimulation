package strategy.kingdom;

import strategy.building.producer.miner.advanced.SarraxMiner;

public class SarraxKingdom implements Kingdom {

    private final SarraxMiner miner;


    public SarraxKingdom() {
        this.miner = new SarraxMiner();
    }
}
