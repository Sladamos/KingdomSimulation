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

    private final OneItemStorage<MilitaryUnit> militaryUnitStorage;

    private int morale;

    private boolean isConsuming;

    public InfantryGeneralImpl(OneItemStorage<Happiness> happinessStorage,
                               OneItemStorage<MilitaryUnit> militaryUnitStorage,
                               GeneralConfig generalConfig) {
        this.happinessStorage = happinessStorage;
        this.militaryUnitStorage = militaryUnitStorage;
        isConsuming = false;
        int happinessDamageModifier = generalConfig.getHappinessDamageModifier();
        morale = happinessDamageModifier;
        army = new ArmyImplBuilder().withDamageModifier(happinessDamageModifier).createNewArmy();
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
                MilitaryUnit unit = militaryUnitStorage.getItemFromStorage();
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
    public void addMilitaryUnits(Collection<MilitaryUnit> militaryUnits) {
	    militaryUnits.forEach(army);
    }

    @Override
    public Army getArmy() {
        return army;
    }

    private synchronized boolean isConsuming() {
        return isConsuming;
    }

    private synchronized void enableConsuming() {
        isConsuming = true;
    }
}
