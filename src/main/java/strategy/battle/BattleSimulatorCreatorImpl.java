package strategy.battle;

import strategy.message.receiver.ConsoleMessagesReceiver;
import strategy.message.receiver.MessagesReceiver;

import java.util.function.Supplier;

public class BattleSimulatorCreatorImpl implements BattleSimulatorCreator {

    private final Supplier<MessagesReceiver> battleMessagesReceiverSupplier;

    public BattleSimulatorCreatorImpl() {
        //TODO: get messages receiver from AppGui -> getBattlesMessagesReceiver or getNewBattleMessagesReceiver
        battleMessagesReceiverSupplier = ConsoleMessagesReceiver::new;
    }

    @Override
    public BattleSimulator createBasicBattleSimulator() {
        return new BasicBattleSimulator(battleMessagesReceiverSupplier.get());
    }
}
