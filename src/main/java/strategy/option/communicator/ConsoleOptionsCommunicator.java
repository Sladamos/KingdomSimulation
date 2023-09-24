package strategy.option.communicator;

import lombok.AllArgsConstructor;
import strategy.option.message.OptionMessagesCreator;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

@AllArgsConstructor
public class ConsoleOptionsCommunicator implements OptionsCommunicator {

    private final OptionMessagesCreator optionMessagesCreator;

    @Override
    public void addKingdomIdProvider(KingdomIdProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutKingdomsId);
    }

    @Override
    public void addBattleIdProvider(BattleIdProvider speakingProvider) {
        speakingProvider.addListener(this::generateMessageAboutBattlesId);
    }

    private void generateMessageAboutKingdomsId() {
        System.out.println(optionMessagesCreator.getCreatedKingdomsId());
    }

    private void generateMessageAboutBattlesId() {
        System.out.println(optionMessagesCreator.getCreatedBattlesId());
    }
}
