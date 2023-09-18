package strategy.battle.operator;

import strategy.battle.Battle;
import strategy.battle.creator.BattleCreator;
import strategy.battle.creator.SimpleBattleCreator;
import strategy.battle.launcher.BattleLauncher;
import strategy.battle.launcher.BattleLauncherImpl;
import strategy.kingdom.Kingdom;

public class BasicBattleOperator implements BattleOperator {

    private final BattleCreator battleCreator;

    private final BattleLauncher battleLauncher;

    public BasicBattleOperator() {
        battleLauncher = new BattleLauncherImpl();
        battleCreator = new SimpleBattleCreator();
    }

    @Override
    public Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        return battleCreator.createBattle(firstKingdom, secondKingdom);
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
