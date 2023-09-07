package strategy.item.military.infantry;

import strategy.item.military.General;

import java.util.Collection;

public interface InfantryGeneral<T extends InfantryUnit> extends General {
    void accept(T infantryUnit);
    void addInfantryUnits(Collection<T> infantryUnits);
}
