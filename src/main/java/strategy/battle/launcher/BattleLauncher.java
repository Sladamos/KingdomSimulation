package strategy.battle.launcher;

import strategy.battle.Battle;

public interface BattleLauncher {
    void launchBattle(Battle battle);
    void waitForBattlesEnd();
    void stopBattle(Battle battle);
}
