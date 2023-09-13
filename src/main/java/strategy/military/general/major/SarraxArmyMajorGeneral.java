package strategy.military.general.major;

import strategy.action.Attack;
import strategy.action.BasicAttack;
import strategy.error.BasicAppError;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.json.JSON;
import strategy.location.castle.CastleStorageManager;
import strategy.location.settlement.SettlementStorageManager;
import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.notifier.MessagesNotifierImpl;
import strategy.message.receiver.MessagesReceiver;
import strategy.military.MilitaryUnit;
import strategy.military.army.Army;
import strategy.military.army.ArmyType;
import strategy.military.general.ArmyGeneral;
import strategy.military.general.ArmyGeneralImpl;
import strategy.military.mechanism.fight.Fightable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SarraxArmyMajorGeneral implements ArmyMajorGeneral {

    private final Map<ArmyType, ArmyGeneral> armiesGenerals;

    private final ExecutorService executor;

    private final OneArgEvent<JSONMessage> messageEvent;

    public SarraxArmyMajorGeneral(SettlementStorageManager settlementStorageManager, CastleStorageManager castleStorageManager, MajorGeneralConfig majorGeneralConfig) {
        executor = Executors.newCachedThreadPool();
        armiesGenerals = new HashMap<>();
        messageEvent = new OneArgEventImpl<>();
        ArmyGeneral warriorsGeneral = new ArmyGeneralImpl(castleStorageManager.getHappinessStorage(),
                settlementStorageManager.getWarriorsStorage(), majorGeneralConfig.getWarriorsGeneralConfig());
        armiesGenerals.put(ArmyType.WARRIOR, warriorsGeneral);
        warriorsGeneral.addListener(this);

    }

    @Override
    public void addUnits(ArmyType armyType, Collection<MilitaryUnit> militaryUnits) {
        if(!armiesGenerals.containsKey(armyType)) {
            throw new BasicAppError("Can't recruit these types of units because general hasn't been recruited.");
        }
        armiesGenerals.get(armyType).addMilitaryUnits(militaryUnits);
    }

    @Override
    public void run() {
        for(var kv : armiesGenerals.entrySet()) {
            executor.submit(() -> kv.getValue().runHappinessConusmer());
            executor.submit(() -> kv.getValue().runUnitsConusmer());
        }
    }

    @Override
    public void terminate() {
        for(var kv : armiesGenerals.entrySet()) {
            kv.getValue().terminate();
        }
    }

    @Override
    public Attack createAttack() {
        Army army = armiesGenerals.get(ArmyType.WARRIOR).getArmy();
        return new BasicAttack(this, Collections.singleton(army.createAttack()));
    }

    @Override
    public void attack(Fightable fightable) {
        Attack attack = createAttack();
        fightable.getHit(attack);
    }

    @Override
    public synchronized void getHit(Attack attack) {
        Collection<Attack> attacks = attack.getCombination();
        Army army = armiesGenerals.get(ArmyType.WARRIOR).getArmy();
        for (Attack att: attacks) {
            army.getHit(att);
        }
    }

    @Override
    public synchronized boolean isDead() {
        return armiesGenerals.get(ArmyType.WARRIOR).getArmy().isDead();
    }

    @Override
    public void removeListeners() {
        messageEvent.removeAllListeners();
    }

    @Override
    public void accept(JSONMessage message) {
        messageEvent.invoke(message);
    }

    @Override
    public void addListener(MessagesReceiver<JSONMessage> messagesReceiver) {
        messageEvent.addListener(messagesReceiver);
    }
}
