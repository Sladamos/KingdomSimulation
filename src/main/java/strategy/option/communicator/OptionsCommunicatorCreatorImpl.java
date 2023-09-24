package strategy.option.communicator;

import strategy.error.CriticalAppError;
import strategy.gui.GUIType;
import strategy.option.message.OptionMessagesCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OptionsCommunicatorCreatorImpl implements OptionsCommunicatorCreator {

    private final Map<GUIType, Supplier<OptionsCommunicator>> communicatorsMap;

    public OptionsCommunicatorCreatorImpl(OptionMessagesCreator messagesCreator) {
        communicatorsMap = new HashMap<>();
        communicatorsMap.put(GUIType.CONSOLE, () -> createConsoleCommunicator());
    }

    private OptionsCommunicator createConsoleCommunicator(OptionMessagesCreator messagesCreator) {
        new ConsoleOptionsCommunicator(messagesCreator, battleIdProvider, kingdomIdProvider);
    }

    @Override
    public OptionsCommunicator createOptionsCommunicator(GUIType guiType) {
        if(!communicatorsMap.containsKey(guiType)) {
            throw new CriticalAppError("Incorrect gui type.");
        }
        return communicatorsMap.get(guiType).get();
    }
}
