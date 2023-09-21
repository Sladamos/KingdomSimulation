package strategy.military.general;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.item.statistic.Happiness;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;
import strategy.military.MilitaryUnit;
import strategy.military.army.Army;
import strategy.military.army.ArmyImplBuilder;
import strategy.storage.OneItemStorage;

import java.util.Collection;

public class ArmyGeneralImpl implements ArmyGeneral {

    private final Army army;

    private final OneItemStorage<Happiness> happinessStorage;

    private final OneItemStorage<MilitaryUnit> militaryUnitStorage;

    private final OneArgEvent<JSONMessage> messageEvent;

    private int morale;

    private boolean isConsuming;

    public ArmyGeneralImpl(OneItemStorage<Happiness> happinessStorage,
                           OneItemStorage<MilitaryUnit> militaryUnitStorage,
                           GeneralConfig generalConfig) {
        this.happinessStorage = happinessStorage;
        this.militaryUnitStorage = militaryUnitStorage;
        messageEvent = new OneArgEventImpl<>();
        isConsuming = false;
        int happinessDamageModifier = morale =  generalConfig.getHappinessDamageModifier();
        army = new ArmyImplBuilder().withDamageModifier(happinessDamageModifier).createNewArmy();
        army.addListener(this);
    }

    @Override
    public synchronized void terminate() {
        isConsuming = false;
    }

    @Override
    public void runUnitsConusmer() {
        enableConsuming();
        while(isConsuming()) {
            try {
                MilitaryUnit unit = militaryUnitStorage.getItem();
                accept(unit);
            } catch (Exception err) {
                return;
            }
        }
    }

    @Override
    public void runHappinessConusmer() {
        enableConsuming();
        while(isConsuming()) {
            try {
                Happiness happiness = happinessStorage.getItem();
                accept(happiness);
            } catch (Exception err) {
                return;
            }
        }
    }

    @Override
    public synchronized void accept(MilitaryUnit militaryUnit) {
        army.accept(militaryUnit);
    }

    @Override
    public synchronized void accept(Happiness happiness) {
        morale++;
        army.onMoraleChanged(morale);
    }

    @Override
    public void addMilitaryUnits(Collection<MilitaryUnit> militaryUnits) {
	    militaryUnits.forEach(army);
    }

    @Override
    public Army getArmy() {
        return army;
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

    private synchronized boolean isConsuming() {
        return isConsuming;
    }

    private synchronized void enableConsuming() {
        isConsuming = true;
    }
}
