package strategy.military.infantry;

import strategy.military.General;

import java.util.Collection;
import java.util.function.Consumer;

public interface InfantryGeneral extends General {
    void accept(InfantryUnit infantryUnit);
    void addInfantryUnits(Collection<InfantryUnit> infantryUnits);
}
