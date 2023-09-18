package strategy.simulation.executioner;

import strategy.battle.Battle;
import strategy.battle.launcher.BattleLauncher;
import strategy.battle.launcher.BattleLauncherImpl;
import strategy.kingdom.Kingdom;
import strategy.kingdom.KingdomConfig;
import strategy.kingdom.operator.KingdomOperator;
import strategy.kingdom.operator.KingdomOperatorImpl;
import strategy.util.Time;

public class SimulationExecutionerImpl implements SimulationExecutioner {

    private final KingdomOperator kingdomOperator;

    private final BattleLauncher battleLauncher;

    public SimulationExecutionerImpl() {
        kingdomOperator = new KingdomOperatorImpl();
        battleLauncher = new BattleLauncherImpl();
    }

    @Override
    public Kingdom createKingdom(KingdomConfig kingdomConfig) {
        return kingdomOperator.createKingdom(kingdomConfig);
    }

    @Override
    public void launchKingdomDeveloping(Kingdom kingdom, Time developmentTime) {
        kingdomOperator.launchKingdom(kingdom, developmentTime.getMiliseconds());
    }

    @Override
    public void waitForKingdomsDevelopingEnd() {
        kingdomOperator.waitForDevelopingEnd();
    }

    @Override
    public void stopKingdom(Kingdom kingdom) {
        kingdomOperator.stopKingdom(kingdom);
    }

    @Override
    public void launchBattle(Battle battle) {
        battleLauncher.launchBattle(battle);
    }

    @Override
    public void waitForBattlesEnd() {
        battleLauncher.waitForBattlesEnd();
    }

    @Override
    public void stopBattle(Battle battle) {
        battleLauncher.stopBattle(battle);
    }
}
