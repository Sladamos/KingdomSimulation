package strategy.initializer;

import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.SarraxKingdom;
import strategy.military.InitMilitaryConfig;
import strategy.military.infantry.InfantryUnit;

import java.util.Collection;

public class KingdomInitializer {
    public Kingdom createKingdom(long sleep, KingdomConfig kingdomConfig) {
        Kingdom kingdom = new SarraxKingdom(kingdomConfig);
        InitMilitaryConfig militaryConfig = kingdomConfig.getWarriorsConfig();
        addWarriorsToKingdom(kingdom, militaryConfig);
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        return kingdom;
    }

    private void addWarriorsToKingdom(Kingdom kingdom, InitMilitaryConfig warriorsConfig) {
        WarriorsInitializer warriorsInitializer = new WarriorsInitializer();
        Collection<InfantryUnit> warriors = warriorsInitializer.createWarriors(warriorsConfig);
        kingdom.addInfantry(warriors);
    }
}
