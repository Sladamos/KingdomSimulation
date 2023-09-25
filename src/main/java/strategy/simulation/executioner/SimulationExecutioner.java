package strategy.simulation.executioner;

import strategy.battle.Battle;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.util.Time;

public interface SimulationExecutioner {
    Kingdom createKingdom(KingdomConfig kingdomConfig);
    void launchKingdomDeveloping(Kingdom kingdom, Time developmentTime);
    void waitForKingdomsDevelopingEnd();
    void stopKingdom(Kingdom kingdom);
    void launchBattle(Battle battle);
    void waitForBattlesEnd();
    void stopBattle(Battle battle);
}
