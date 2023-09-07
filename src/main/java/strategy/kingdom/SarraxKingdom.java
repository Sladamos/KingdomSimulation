package strategy.kingdom;

import lombok.Getter;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.location.castle.Castle;
import strategy.location.castle.SarraxCastle;
import strategy.location.mountain.Mountain;
import strategy.location.mountain.SarraxMountain;
import strategy.location.settlement.SarraxSettlement;
import strategy.location.settlement.Settlement;
import strategy.location.village.SarraxVillage;
import strategy.item.military.infantry.InfantryUnit;
import strategy.location.village.Village;

import java.util.Collection;

public class SarraxKingdom implements Kingdom {

    private final Mountain mountain;

    private final Settlement settlement;

    private final Village village;

    @Getter
    private final Castle<Warrior> castle;

    private final long attackTime;

    private final String kingdomId;

    public SarraxKingdom(KingdomConfig kingdomConfig) {
        KingdomStorageManager kingdomStorageManager = new SarraxKingdomStorageManager();
        mountain = new SarraxMountain(kingdomStorageManager.getMountainStorageManager());
        village = new SarraxVillage(kingdomStorageManager.getVillageStorageManager(), null);
        settlement = new SarraxSettlement();
        castle = new SarraxCastle<>(kingdomStorageManager.getCastleStorageManager());
        this.kingdomId = kingdomConfig.getKingdomId();
        this.attackTime = kingdomConfig.getAttackTime();
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
    public void addWarriors(Collection<Warrior> infantryUnits) {
        castle.addInfantry(infantryUnits);
    }

    @Override
    public long getAttackTime() {
        return attackTime;
    }

    @Override
    public String toString() {
        return kingdomId + " kingdom";
    }
}
