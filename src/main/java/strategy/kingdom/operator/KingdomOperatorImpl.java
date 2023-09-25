package strategy.kingdom.operator;

import strategy.initializer.kingdom.KingdomInitializerImpl;
import strategy.initializer.kingdom.KingdomInitializer;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.launcher.KingdomLauncher;
import strategy.kingdom.launcher.KingdomLauncherImpl;

public class KingdomOperatorImpl implements KingdomOperator {
    
    private final KingdomInitializer kingdomInitializer;

    private final KingdomLauncher kingdomLauncher;

    public KingdomOperatorImpl() {
        kingdomInitializer = new KingdomInitializerImpl();
        kingdomLauncher = new KingdomLauncherImpl();
    }

    @Override
    public Kingdom createKingdom(KingdomConfig kingdomConfig) {
        return kingdomInitializer.createKingdom(kingdomConfig);
    }

    @Override
    public void launchKingdom(Kingdom kingdom, long miliseconds) {
        kingdomLauncher.launchKingdom(kingdom, miliseconds);
    }

    @Override
    public void waitForDevelopingEnd() {
        kingdomLauncher.waitForDevelopingEnd();
    }

    @Override
    public void stopKingdom(Kingdom kingdom) {
        kingdomLauncher.stopKingdom(kingdom);
    }
}
