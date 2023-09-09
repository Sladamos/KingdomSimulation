package strategy.battle;

import strategy.kingdom.Kingdom;
import strategy.message.receiver.MessagesReceiver;

public class BasicBattleSimulator implements BattleSimulator {
    private final MessagesReceiver battleMessagesReceiver;

    public BasicBattleSimulator(MessagesReceiver battleMessagesReceiver) {
        this.battleMessagesReceiver = battleMessagesReceiver;
    }

    @Override
    public void simulateBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        Battle battle = new SimpleBattle(firstKingdom, secondKingdom, battleMessagesReceiver);
        Thread thread = new Thread(battle);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
