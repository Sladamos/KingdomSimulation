package strategy.item.military.infantry;

import strategy.item.military.ArmyDestroyedException;
import strategy.producer.exceptions.ProducerTerminatedException;
import strategy.item.statistic.Happiness;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Supplier;

public class HumanInfantryGeneral implements InfantryGeneral {

    Collection<InfantryUnit> army;

    private final Supplier<Happiness> happinessSupplier;

    private final Supplier<InfantryUnit> infantryUnitSupplier;

    private int damageModificator;

    private static final int HAPPINESS_DAMAGE_MODIFICATOR = 2;

    private boolean isConsuming;

    public HumanInfantryGeneral(Supplier<Happiness> happinessSupplier, Supplier<InfantryUnit> infantryUnitSupplier) {
        this.happinessSupplier = happinessSupplier;
        this.infantryUnitSupplier = infantryUnitSupplier;
        isConsuming = false;
        damageModificator = HAPPINESS_DAMAGE_MODIFICATOR;
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
                InfantryUnit unit = infantryUnitSupplier.get();
                accept(unit);
            } catch (ProducerTerminatedException err) {
                return;
            }
        }
    }

    @Override
    public void runHappinessConusmer() {
        isConsuming = true;
        while(isConsuming()) {
            try {
                Happiness happiness = happinessSupplier.get();
                accept(happiness);
            } catch (ProducerTerminatedException err) {
                return;
            }
        }
    }

    @Override
    public synchronized Collection<Integer> getArmyDamage() {
        Collection<Integer> damages = new LinkedList<>();
        for(var infantry : army) {
            damages.add(infantry.getDamage() * (int)(damageModificator / HAPPINESS_DAMAGE_MODIFICATOR));
        }
        return damages;
    }

    @Override
    public synchronized void receiveDamage(Collection<Integer> damages) {
        var it = army.iterator();
        InfantryUnit unit = getUnitToHit(it);
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
        System.out.println("Get attacked, infantry left: " + army.size());
        if(!hasArmy())
            throw new ArmyDestroyedException();
    }

    @Override
    public synchronized void accept(InfantryUnit infantryUnit) {
        System.out.println("Infantry unit consumed");
        army.add(infantryUnit);
    }

    @Override
    public synchronized void addInfantryUnits(Collection<InfantryUnit> infantryUnits) {
        army.addAll(infantryUnits);
    }

    @Override
    public synchronized void accept(Happiness happiness) {
        System.out.println("Happiness consumed");
        damageModificator++;
    }

    private InfantryUnit getUnitToHit(Iterator<InfantryUnit> it) {
        if(!it.hasNext()) {
            throw new ArmyDestroyedException();
        }
        return it.next();
    }

    private boolean hasArmy() {
        return army.size() > 0;
    }

    private synchronized boolean isConsuming() {
        return isConsuming;
    }
}
