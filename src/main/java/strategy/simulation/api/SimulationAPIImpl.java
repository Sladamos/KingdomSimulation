package strategy.simulation.api;

import strategy.battle.Battle;
import strategy.battle.BattleConfig;
import strategy.battle.creator.BattleCreator;
import strategy.error.BasicAppError;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.executioner.SimulationExecutioner;
import strategy.simulation.executioner.SimulationExecutionerImpl;
import strategy.util.Time;
import strategy.util.TimeImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimulationAPIImpl implements SimulationAPI {

    private final SimulationExecutioner simulationExecutioner;

    private final Map<Integer, Battle> battleMap;

    private final Map<String, Kingdom> kingdomMap;

    public SimulationAPIImpl() {
        simulationExecutioner = new SimulationExecutionerImpl();
        kingdomMap = new HashMap<>();
        battleMap = new HashMap<>();
    }

    @Override
    public void stopSimulation() {
        for(var kv: kingdomMap.entrySet()) {
            Kingdom kingdom = kv.getValue();
            simulationExecutioner.stopKingdom(kingdom);
        }

        for(var kv: battleMap.entrySet()) {
            Battle battle = kv.getValue();
            simulationExecutioner.stopBattle(battle);
        }
        simulationExecutioner.waitForKingdomsDevelopingEnd();
        simulationExecutioner.waitForBattlesEnd();
    }

    @Override
    public void launchKingdom(String kingdomId) {
        if(!kingdomMap.containsKey(kingdomId)) {
            throw new BasicAppError("Incorrect kingdom ID");
        }
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.launchKingdomDeveloping(kingdom, new TimeImpl(Integer.MAX_VALUE));
    }

    @Override
    public void stopKingdom(String kingdomId) {
        if(!kingdomMap.containsKey(kingdomId)) {
            throw new BasicAppError("Incorrect kingdom ID");
        }
        Kingdom kingdom = kingdomMap.get(kingdomId);
        simulationExecutioner.stopKingdom(kingdom);
    }

    @Override
    public void stopBattle(Integer battleId) {
        if (!battleMap.containsKey(battleId)) {
            throw new BasicAppError("Incorrect battle id");
        }

        Battle battle = battleMap.get(battleId);
        simulationExecutioner.stopBattle(battle);
    }

    @Override
    public Battle launchBattle(BattleCreator battleCreator, BattleConfig battleConfig) {
        Integer battleId = battleConfig.getBattleId();
        String firstKingdomId = battleConfig.getFirstKingdomId();
        String secondKingdomId = battleConfig.getSecondKingdomId();

        if (battleMap.containsKey(battleId)) {
            throw new BasicAppError("Incorrect battle id");
        }
        if (!kingdomMap.containsKey(firstKingdomId)) {
            throw new BasicAppError("Incorrect first kingdom id");
        }
        if (!kingdomMap.containsKey(secondKingdomId)) {
            throw new BasicAppError("Incorrect second kingdom id");
        }

        Kingdom firstKingdom = kingdomMap.get(firstKingdomId);
        Kingdom secondKingdom = kingdomMap.get(secondKingdomId);
        Battle battle = battleCreator.createBattle(firstKingdom, secondKingdom);
        simulationExecutioner.launchBattle(battle);
        battleMap.put(battleId, battle);
        return battle;
    }

    @Override
    public Collection<String> getCreatedKingdomsId() {
        return kingdomMap.keySet();
    }

    @Override
    public Collection<Integer> getCreatedBattlesId() {
        return battleMap.keySet();
    }

    @Override
    public Kingdom createKingdom(KingdomConfig kingdomConfig) {
        simulationExecutioner.createKingdom(kingdomConfig);
        Kingdom kingdom = simulationExecutioner.createKingdom(kingdomConfig);
        kingdomMap.put(kingdom.getId(), kingdom);
        return kingdom;
    }

    @Override
    public void launchKingdomDeveloping(Kingdom kingdom, Time developmentTime) {
        simulationExecutioner.launchKingdomDeveloping(kingdom, developmentTime);
    }

    @Override
    public void waitForKingdomsDevelopingEnd() {
        simulationExecutioner.waitForKingdomsDevelopingEnd();
    }

    @Override
    public void stopKingdom(Kingdom kingdom) {
        simulationExecutioner.stopKingdom(kingdom);
    }

    @Override
    public void launchBattle(Battle battle) {
        simulationExecutioner.launchBattle(battle);
    }

    @Override
    public void waitForBattlesEnd() {
        simulationExecutioner.waitForBattlesEnd();
    }

    @Override
    public void stopBattle(Battle battle) {
        simulationExecutioner.stopBattle(battle);
    }
}
