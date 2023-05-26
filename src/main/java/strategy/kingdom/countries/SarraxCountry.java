package strategy.kingdom.countries;

import strategy.kingdom.building.producer.mine.advanced.SarraxMiner;

public class SarraxCountry implements Country {

    private final SarraxMiner miner;


    public SarraxCountry() {
        this.miner = new SarraxMiner();
    }
}
