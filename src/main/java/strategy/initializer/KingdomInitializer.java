package strategy.initializer;

import strategy.item.military.InitMilitaryConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomTypes;
import strategy.kingdom.SarraxKingdom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class KingdomInitializer {

    private final Map<KingdomTypes, Function<KingdomConfig, Kingdom>> kingdomsCreators;

    public KingdomInitializer() {
        kingdomsCreators = new HashMap<>();
        kingdomsCreators.put(KingdomTypes.SARRAX, SarraxKingdom::new);
    }

    public Kingdom createKingdom(long sleep, KingdomConfig kingdomConfig) {
        KingdomTypes kingdomType = kingdomConfig.getKingdomType();
        Kingdom kingdom = kingdomsCreators.get(kingdomType).apply(kingdomConfig);
        InitMilitaryConfig militaryConfig = kingdomConfig.getMilitaryConfig();
        addWarriorsToKingdom(kingdom, militaryConfig);
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        return kingdom;
    }

    private void addWarriorsToKingdom(Kingdom kingdom, InitMilitaryConfig warriorsConfig) {
        RandomWarriorsInitializer warriorsInitializer = new RandomWarriorsInitializer();
        Collection<Warrior> warriors = warriorsInitializer.createWarriors(warriorsConfig);
        kingdom.addWarriors(warriors);
    }
}
