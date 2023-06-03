package strategy.military.infantry;

import strategy.military.General;

import java.util.function.Consumer;

public interface InfantryGeneral extends General {
    void accept(InfantryUnit infantryUnit);
}
