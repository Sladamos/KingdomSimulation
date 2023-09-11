package strategy.battle.operator;

import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.notifier.MessagesNotifierImpl;

import java.util.function.Supplier;

public class BattleOperatorCreatorImpl implements BattleOperatorCreator {

    private final Supplier<MessagesNotifier<StringMessage>> battleMessagesNotifierSupplier;

    public BattleOperatorCreatorImpl() {
        battleMessagesNotifierSupplier = MessagesNotifierImpl::new;
    }

    @Override
    public BattleOperator createBasicBattleOperator() {
        return new BasicBattleOperator(battleMessagesNotifierSupplier);
    }
}
