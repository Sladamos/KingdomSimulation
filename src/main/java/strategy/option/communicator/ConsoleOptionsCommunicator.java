package strategy.option.communicator;

import lombok.AllArgsConstructor;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.SpeakingProvider;

@AllArgsConstructor
public class ConsoleOptionsCommunicator implements OptionsCommunicator {

    private final OptionMessagesCreator optionMessagesCreator;

    @Override
    public void addKingdomIdProvider(SpeakingProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutKingdomsId);
    }

    @Override
    public void addBattleIdProvider(SpeakingProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutBattlesId);
    }

    private void generateMessageAboutKingdomsId() {
        System.out.println(optionMessagesCreator.getCreatedKingdomsId());
    }

    private void generateMessageAboutBattlesId() {
        System.out.println(optionMessagesCreator.getCreatedBattlesId());
    }
}
