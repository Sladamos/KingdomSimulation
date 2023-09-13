package strategy.kingdom;

import lombok.Getter;
import strategy.action.Attack;
import strategy.action.BasicAttack;
import strategy.military.MilitaryUnit;
import strategy.military.army.ArmyType;
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
import strategy.military.mechanism.fight.Fightable;
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
        majorKingdomGeneral = new SarraxArmyMajorGeneral(settlementStorageManager, castleStorageManager, kingdomConfig.getMajorGeneralConfig());
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
    public void addMilitaryUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits) {
        majorKingdomGeneral.addUnits(armyType, militaryUnits);
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

    @Override
    public Attack createAttack() {
        return new BasicAttack(this, majorKingdomGeneral.createAttack().getCombination());
    }

    @Override
    public void attack(Fightable fightable) {
        Attack attack = createAttack();
        fightable.getHit(attack);
    }

    @Override
    public void getHit(Attack attack) {
        majorKingdomGeneral.getHit(attack);
    }

    @Override
    public synchronized boolean isDead() {
        return majorKingdomGeneral.isDead();
    }
}
