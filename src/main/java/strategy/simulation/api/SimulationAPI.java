package strategy.simulation.api;

import strategy.battle.Battle;
import strategy.battle.BattleConfig;
import strategy.battle.creator.BattleCreator;
import strategy.simulation.executioner.SimulationExecutioner;

import java.util.Collection;

public interface SimulationAPI extends SimulationExecutioner {
    void stopSimulation();
    void launchKingdom(String kingdomId);
    void stopKingdom(String kingdomId);
    void stopBattle(Integer battleId);
    Battle launchBattle(BattleCreator battleCreator, BattleConfig battleConfig);
    Collection<String> getCreatedKingdomsId();
    Collection<Integer> getCreatedBattlesId();
}
