package strategy.item.military.infantry;

import strategy.item.military.General;

import java.util.Collection;

public interface InfantryGeneral extends General {
    void accept(InfantryUnit infantryUnit);
    void addInfantryUnits(Collection<InfantryUnit> infantryUnits);
}
