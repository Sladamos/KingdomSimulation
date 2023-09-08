package strategy.item.military.infantry;

import strategy.item.military.ArmyDestroyedException;
import strategy.item.military.GeneralConfig;
import strategy.item.statistic.Happiness;
import strategy.storage.OneItemStorage;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class InfantryGeneralImpl<T extends InfantryUnit> implements InfantryGeneral<T> {

    private final Collection<T> army;

    private final OneItemStorage<Happiness> happinessStorage;

    private final OneItemStorage<T> infantryUnitStorage;

    private int damageModificator;

    private final int happinessDamageModificator;

    private boolean isConsuming;

    public InfantryGeneralImpl(OneItemStorage<Happiness> happinessStorage,
                               OneItemStorage<T> infantryUnitStorage,
                               GeneralConfig generalConfig) {
        this.happinessStorage = happinessStorage;
        this.infantryUnitStorage = infantryUnitStorage;
        isConsuming = false;
        happinessDamageModificator = generalConfig.getHappinessDamageModificator();
        army = new LinkedList<>();
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
                T unit = infantryUnitStorage.getItemFromStorage();
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
    public synchronized Collection<Integer> getArmyDamage() {
        int modificator = damageModificator / happinessDamageModificator;
        return army.stream().map(el -> el.getDamage() * modificator).toList();
    }

    @Override
    public synchronized void receiveDamage(Collection<Integer> damages) {
        var it = army.iterator();
        T unit = getUnitToHit(it);
        for(Integer damage : damages) {
            try {
                unit.getHit(damage);
            }  finally {
                if(unit.getHitPoints() == 0) {
                    it.remove();
                    unit = getUnitToHit(it);
                }
            }
        }
        //TODO REMOVE
        System.out.println("Get attacked, infantry left: " + army.size());
        if(!hasArmy())
            throw new ArmyDestroyedException();
    }

    @Override
    public synchronized void accept(T infantryUnit) {
        army.add(infantryUnit);
    }

    @Override
    public synchronized void accept(Happiness happiness) {
        damageModificator++;
    }

    @Override
    public synchronized void addInfantryUnits(Collection<T> infantryUnits) {
        army.addAll(infantryUnits);
    }

    private synchronized T getUnitToHit(Iterator<T> it) {
        if(!it.hasNext()) {
            throw new ArmyDestroyedException();
        }
        return it.next();
    }

    private synchronized boolean hasArmy() {
        return army.size() > 0;
    }

    private synchronized boolean isConsuming() {
        return isConsuming;
    }
}