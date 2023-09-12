package strategy.item.military.infantry;

import strategy.item.military.MilitaryUnit;
import strategy.item.military.army.Army;
import strategy.item.military.army.ArmyImplBuilder;
import strategy.item.military.general.GeneralConfig;
import strategy.item.statistic.Happiness;
import strategy.storage.OneItemStorage;

import java.util.Collection;

public class InfantryGeneralImpl implements InfantryGeneral {

    private final Army army;

    private final OneItemStorage<Happiness> happinessStorage;

    private final OneItemStorage<InfantryUnit> infantryUnitStorage;

    private int morale;

    private boolean isConsuming;

    public InfantryGeneralImpl(OneItemStorage<Happiness> happinessStorage,
                               OneItemStorage<InfantryUnit> infantryUnitStorage,
                               GeneralConfig generalConfig) {
        this.happinessStorage = happinessStorage;
        this.infantryUnitStorage = infantryUnitStorage;
        isConsuming = false;
        int happinessDamageModificator = generalConfig.getHappinessDamageModificator();
        morale = happinessDamageModificator;
        army = new ArmyImplBuilder().withDamageModifier(happinessDamageModificator).createNewArmy();
    }

    @Override
    public synchronized void terminate() {
        isConsuming = false;
    }

    @Override
    public void runUnitsConusmer() {
        isConsuming = true;
        while(isConsuming()) {
            try {
                InfantryUnit unit = infantryUnitStorage.getItemFromStorage();
                accept(unit);
            } catch (Exception err) {
                return;
            }
        }
    }

    @Override
    public void runHappinessConusmer() {
        isConsuming = true;
        while(isConsuming()) {
            try {
                Happiness happiness = happinessStorage.getItemFromStorage();
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
        army.setMorale(morale);
    }

    @Override
    public synchronized void addMilitaryUnits(Collection<MilitaryUnit> militaryUnits) {
        army.addAll(militaryUnits);
    }

    @Override
    public Army getArmy() {
        return army;
    }

    private synchronized boolean isConsuming() {
        return isConsuming;
    }
}
