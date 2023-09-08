package strategy.kingdom;

import lombok.Getter;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.location.castle.Castle;
import strategy.location.castle.CastleStorageManager;
import strategy.location.castle.SarraxCastle;
import strategy.location.mountain.Mountain;
import strategy.location.mountain.MountainStorageManager;
import strategy.location.mountain.SarraxMountain;
import strategy.location.settlement.SarraxSettlement;
import strategy.location.settlement.Settlement;
import strategy.location.settlement.SettlementStorageManager;
import strategy.location.village.SarraxVillage;
import strategy.location.village.Village;
import strategy.location.village.VillageStorageManager;

import java.util.Collection;

public class SarraxKingdom implements Kingdom {

    private final Mountain mountain;

    private final Settlement settlement;

    private final Village village;

    private final KingdomStorageManager<Warrior> kingdomStorageManager;

    @Getter
    private final Castle<Warrior> castle;

    private final long attackTime;

    private final String kingdomId;

    public SarraxKingdom(KingdomConfig kingdomConfig) {
        kingdomStorageManager = new SarraxKingdomStorageManager();
        MountainStorageManager mountainStorageManager = kingdomStorageManager.getMountainStorageManager();
        SettlementStorageManager<Warrior> settlementStorageManager = kingdomStorageManager.getSettlementStorageManager();
        VillageStorageManager villageStorageManager = kingdomStorageManager.getVillageStorageManager();
        CastleStorageManager castleStorageManager = kingdomStorageManager.getCastleStorageManager();
        mountain = new SarraxMountain(mountainStorageManager, kingdomConfig.getMountainConfig());
        village = new SarraxVillage(villageStorageManager, settlementStorageManager.getWaterStorage(), kingdomConfig.getVillageConfig());
        settlement = new SarraxSettlement(settlementStorageManager, villageStorageManager,
                mountainStorageManager, castleStorageManager.getAdultStorage());
        castle = new SarraxCastle<>(castleStorageManager, settlementStorageManager, kingdomConfig.getCastleConfig());
        this.kingdomId = kingdomConfig.getKingdomId();
        this.attackTime = kingdomConfig.getAttackTime();
    }

    @Override
    public void run() {
        kingdomStorageManager.enableAcceptingItems();
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
        kingdomStorageManager.disableAcceptingItems();
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
