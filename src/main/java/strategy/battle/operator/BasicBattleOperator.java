package strategy.battle.operator;

import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.battle.operator.BattleOperator;
import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;

import java.util.function.Supplier;

public class BasicBattleOperator implements BattleOperator {
    private final Supplier<MessagesNotifier<StringMessage>> basicMessagesNotifierSupplier;

    public BasicBattleOperator(Supplier<MessagesNotifier<StringMessage>> basicMessagesNotifierSupplier) {
        this.basicMessagesNotifierSupplier = basicMessagesNotifierSupplier;
    }

    @Override
    public Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        return new SimpleBattle(firstKingdom, secondKingdom, basicMessagesNotifierSupplier.get());
    }

    @Override
    public void simulateBattle(Battle battle) {
        Thread thread = new Thread(battle);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
