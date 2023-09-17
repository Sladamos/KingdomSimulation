package strategy.battle;

public interface BattleSimulator {
    void addBattleToSimulator(Battle battle);
    void waitForBattlesEnd();
}
