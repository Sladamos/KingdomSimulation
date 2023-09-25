package strategy.kingdom.launcher;

import strategy.kingdom.Kingdom;

public interface KingdomLauncher {
    void launchKingdom(Kingdom kingdom, long miliseconds);
    void waitForDevelopingEnd();
    void stopKingdom(Kingdom kingdom);
}
