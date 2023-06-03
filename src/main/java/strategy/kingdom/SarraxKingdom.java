package strategy.kingdom;

import lombok.Getter;
import strategy.location.castle.Castle;
import strategy.location.castle.SarraxCastle;
import strategy.location.mountain.SarraxMountain;
import strategy.location.settlement.SarraxSettlement;
import strategy.location.village.SarraxVillage;
import strategy.military.infantry.InfantryUnit;

import java.util.Collection;

public class SarraxKingdom implements Kingdom {

    private final SarraxMountain mountain;

    private final SarraxSettlement settlement;

    private final SarraxVillage village;

    @Getter
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

    @Override
    public void terminate() {
        mountain.terminate();
        settlement.terminate();
        village.terminate();
        castle.terminate();
    }

    @Override
    public void attack(Kingdom kingdom) {
        castle.attack(kingdom.getCastle());
    }

    @Override
    public void addInfantry(Collection<InfantryUnit> infantryUnits) {
        castle.addInfantry(infantryUnits);
    }

}
