package strategy.option.communicator;

import strategy.buffer.Buffer;
import strategy.buffer.BufferImpl;
import strategy.error.CriticalAppError;
import strategy.gui.GUIType;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.BufferedProvidersCreator;
import strategy.provider.ProvidersCreator;
import strategy.provider.battle.SpeakingBattleIdProvider;
import strategy.provider.kingdom.SpeakingKingdomIdProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class OptionsCommunicatorCreatorImpl implements OptionsCommunicatorCreator {

    private final Map<GUIType, Supplier<OptionsCommunicator>> communicatorsMap;

    private final Buffer<String> communicationBuffer;

    private final ProvidersCreator providersCreator;

    public OptionsCommunicatorCreatorImpl(OptionMessagesCreator messagesCreator) {
        communicationBuffer = new BufferImpl<>();
        providersCreator = new BufferedProvidersCreator(communicationBuffer);
        communicatorsMap = new HashMap<>();
        communicatorsMap.put(GUIType.CONSOLE, () -> createConsoleCommunicator(messagesCreator));
    }

    private OptionsCommunicator createConsoleCommunicator(OptionMessagesCreator messagesCreator) {
        SpeakingBattleIdProvider battleIdProvider = providersCreator.createSpeakingBattleIdProvider();
        SpeakingKingdomIdProvider kingdomIdProvider = providersCreator.createSpeakingKingdomIdProvider();
        return new BufferedConsoleOptionsCommunicator(messagesCreator, communicationBuffer, battleIdProvider, kingdomIdProvider);
    }

    @Override
    public OptionsCommunicator createOptionsCommunicator(GUIType guiType) {
        if(!communicatorsMap.containsKey(guiType)) {
            throw new CriticalAppError("Incorrect gui type.");
        }
        return communicatorsMap.get(guiType).get();
    }
}
