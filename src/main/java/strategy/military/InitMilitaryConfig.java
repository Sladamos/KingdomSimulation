package strategy.military;

import strategy.Config;

public interface InitMilitaryConfig extends Config {
    int getNumberOfUnits();
    int getMaxDamage();
    int getMaxDefense();
}
