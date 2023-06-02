package strategy.kingdom;

import strategy.location.castle.SarraxCastle;
import strategy.location.mountain.SarraxMountain;
import strategy.location.settlement.SarraxSettlement;
import strategy.location.village.SarraxVillage;
import strategy.producer.building.miner.advanced.SarraxMiner;

public class SarraxKingdom implements Kingdom {

    private final SarraxMountain mountain;

    private final SarraxSettlement settlement;

    private final SarraxVillage village;

    private final SarraxCastle castle;

    public SarraxKingdom() {
        mountain = new SarraxMountain();
        village = new SarraxVillage();
        settlement = new SarraxSettlement(mountain, village);
        village.setWaterProducer(settlement::getWater);
        castle = new SarraxCastle(settlement);
        settlement.setAdultsProducer(castle::getAdult);
    }

    @Override
    public void run() {
        mountain.run();
        settlement.run();
        village.run();
        castle.run();
    }
}
