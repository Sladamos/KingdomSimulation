package strategy.kingdom;

import lombok.Getter;
import strategy.military.general.major.ArmyMajorGeneral;
import strategy.military.general.major.SarraxArmyMajorGeneral;
import strategy.military.infantry.InfantryUnit;
import strategy.kingdom.notifier.KingdomMessagesNotifier;
import strategy.kingdom.notifier.KingdomMessagesNotifierImpl;
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
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;
import strategy.util.Time;

import java.util.Collection;

public class SarraxKingdom implements Kingdom {

    @Getter
    private final Castle castle;

    private final Mountain mountain;

    private final Settlement settlement;

    private final Village village;

    private final KingdomConfig kingdomConfig;

    private final KingdomStorageManager kingdomStorageManager;

    private final KingdomMessagesNotifier kingdomMessagesNotifier;

    private final ArmyMajorGeneral majorKingdomGeneral;

    public SarraxKingdom(KingdomConfig kingdomConfig) {
        this.kingdomConfig = kingdomConfig;
        kingdomStorageManager = new SarraxKingdomStorageManager();
        MountainStorageManager mountainStorageManager = kingdomStorageManager.getMountainStorageManager();
        SettlementStorageManager settlementStorageManager = kingdomStorageManager.getSettlementStorageManager();
        VillageStorageManager villageStorageManager = kingdomStorageManager.getVillageStorageManager();
        CastleStorageManager castleStorageManager = kingdomStorageManager.getCastleStorageManager();

        mountain = new SarraxMountain(mountainStorageManager, kingdomConfig.getMountainConfig());

        village = new SarraxVillage(villageStorageManager, settlementStorageManager.getWaterStorage(),
                kingdomConfig.getVillageConfig());

        settlement = new SarraxSettlement(settlementStorageManager, villageStorageManager,
                mountainStorageManager, castleStorageManager.getAdultStorage(), kingdomConfig.getSettlementConfig());

        castle = new SarraxCastle(castleStorageManager, settlementStorageManager, kingdomConfig.getCastleConfig());

        kingdomMessagesNotifier = new KingdomMessagesNotifierImpl(kingdomConfig.getKingdomId());
        kingdomStorageManager.getStorageMessagesNotifier().addListener(kingdomMessagesNotifier);
        majorKingdomGeneral = new SarraxArmyMajorGeneral(settlementStorageManager, castleStorageManager);
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
        kingdomStorageManager.disableAcceptingItems();
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
    public void addInfantryUnits(Collection<InfantryUnit> infantryUnits) {
        castle.addInfantry(infantryUnits);
    }

    @Override
    public Time getAttackTime() {
        return kingdomConfig.getAttackTime();
    }

    @Override
    public String toString() {
        return kingdomConfig.getKingdomId() + " kingdom";
    }

    @Override
    public void addListener(MessagesReceiver<JSONMessage> messagesReceiver) {
        kingdomMessagesNotifier.addListener(messagesReceiver);
    }
}
