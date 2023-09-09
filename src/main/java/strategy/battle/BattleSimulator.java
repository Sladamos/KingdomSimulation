package strategy.battle;

import strategy.kingdom.Kingdom;

public interface BattleSimulator {
    void simulateBattle(Kingdom firstKingdom, Kingdom secondKingdom);
}
