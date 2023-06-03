package strategy.military.infantry;

import strategy.military.ArmyDestroyedException;
import strategy.organism.mechanisms.fight.exceptions.FightActionException;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HumanInfantryGeneral implements InfantryGeneral {

    Collection<InfantryUnit> army;

    public HumanInfantryGeneral(int numberOfWarriors, int warriorDamage, int warriorDefense) {
        army = new LinkedList<>();
        while(numberOfWarriors > 0) {
            army.add(new Warrior(warriorDamage, warriorDefense));
            numberOfWarriors--;
        }
    }

    @Override
    public synchronized Collection<Integer> getArmyDamage() {
        Collection<Integer> damages = new LinkedList<>();
        for(var infantry : army) {
            damages.add(infantry.getDamage());
            //TODO happiness and new warriors
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
        if(!hasArmy())
            throw new ArmyDestroyedException();
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
}
