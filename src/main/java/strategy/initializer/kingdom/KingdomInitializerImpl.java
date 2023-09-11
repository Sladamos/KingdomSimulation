package strategy.initializer.kingdom;

import strategy.initializer.kingdom.KingdomInitializer;
import strategy.initializer.military.RandomWarriorsInitializer;
import strategy.item.military.infantry.warrior.InitWarriorsConfig;
import strategy.item.military.infantry.warrior.Warrior;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomType;
import strategy.kingdom.SarraxKingdom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class KingdomInitializerImpl implements KingdomInitializer {

    private final Map<KingdomType, Function<KingdomConfig, Kingdom>> kingdomsCreators;

    public KingdomInitializerImpl() {
        kingdomsCreators = new HashMap<>();
        kingdomsCreators.put(KingdomType.SARRAX, SarraxKingdom::new);
    }

    @Override
    public Kingdom createKingdom(KingdomConfig kingdomConfig) {
        KingdomType kingdomType = kingdomConfig.getKingdomType();
        Kingdom kingdom = kingdomsCreators.get(kingdomType).apply(kingdomConfig);
        InitWarriorsConfig warriorsConfig = kingdomConfig.getWarriorsConfig();
        addWarriorsToKingdom(kingdom, warriorsConfig);
        return kingdom;
    }

    private void addWarriorsToKingdom(Kingdom kingdom, InitWarriorsConfig warriorsConfig) {
        RandomWarriorsInitializer warriorsInitializer = new RandomWarriorsInitializer();
        Collection<Warrior> warriors = warriorsInitializer.createWarriors(warriorsConfig);
        kingdom.addWarriors(warriors);
    }
}
