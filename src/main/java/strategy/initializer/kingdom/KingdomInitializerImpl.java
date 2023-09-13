package strategy.initializer.kingdom;

import strategy.error.CriticalAppError;
import strategy.initializer.military.RandomWarriorsInitializer;
import strategy.military.infantry.warrior.InitWarriorsConfig;
import strategy.military.infantry.warrior.Warrior;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.KingdomType;
import strategy.kingdom.SarraxKingdom;

import java.util.*;
import java.util.function.Function;

public class KingdomInitializerImpl implements KingdomInitializer {

    private final Map<KingdomType, Function<KingdomConfig, Kingdom>> kingdomsCreators;

    private final Set<String> forbiddenKingdomsIds;

    public KingdomInitializerImpl() {
        kingdomsCreators = new HashMap<>();
        kingdomsCreators.put(KingdomType.SARRAX, SarraxKingdom::new);
        forbiddenKingdomsIds = new HashSet<>();
    }

    @Override
    public Kingdom createKingdom(KingdomConfig kingdomConfig) {
        String kingdomId = kingdomConfig.getKingdomId();
        if(forbiddenKingdomsIds.contains(kingdomId)) {
            throw new CriticalAppError("Incorrect kingdom ID: " + kingdomId);
        }
        forbiddenKingdomsIds.add(kingdomId);
        KingdomType kingdomType = kingdomConfig.getKingdomType();
        Kingdom kingdom = kingdomsCreators.get(kingdomType).apply(kingdomConfig);
        InitWarriorsConfig warriorsConfig = kingdomConfig.getWarriorsConfig();
        addWarriorsToKingdom(kingdom, warriorsConfig);
        return kingdom;
    }

    private void addWarriorsToKingdom(Kingdom kingdom, InitWarriorsConfig warriorsConfig) {
        RandomWarriorsInitializer warriorsInitializer = new RandomWarriorsInitializer();
        Collection<Warrior> warriors = warriorsInitializer.createWarriors(warriorsConfig);
        kingdom.addInfantryUnits(new LinkedList<>(warriors));
    }
}
