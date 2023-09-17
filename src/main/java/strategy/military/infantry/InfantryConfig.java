package strategy.military.infantry;

import strategy.Config;

public interface InfantryConfig extends Config {

    int getDamage();

    int getDefense();

    int getHealth();
}
