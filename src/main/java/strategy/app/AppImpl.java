package strategy.app;

import strategy.battle.BattleConfig;
import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

import java.util.function.Consumer;

public class AppImpl implements App {
    
    private final AppCommunicator appCommunicator;

    private final AppInputHandler appInputHandler;

    public AppImpl(AppInputHandler appInputHandler, AppCommunicator appCommunicator) {
        this.appInputHandler = appInputHandler;
        this.appCommunicator = appCommunicator;
    }

    @Override
    public void bindErrorsSender(MessagesSender<JSONMessage> errorsSender) {
        appCommunicator.bindErrorsSender(errorsSender);
    }

    @Override
    public void bindBattleSender(MessagesSender<StringMessage> battleSender) {
        appCommunicator.bindBattleSender(battleSender);
    }

    @Override
    public void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender) {
        appCommunicator.bindKingdomSender(kingdomSender);
    }

    @Override
    public void receiveErrorMessage(JSONMessage message) {
        appCommunicator.receiveErrorMessage(message);
    }

    @Override
    public void receiveMessageFromBattle(StringMessage message) {
        appCommunicator.receiveMessageFromBattle(message);
    }

    @Override
    public void receiveMessageFromKingdom(JSONMessage message) {
        appCommunicator.receiveMessageFromKingdom(message);
    }

    @Override
    public void enableInputHandling() {
        appInputHandler.enableInputHandling();
    }

    @Override
    public void onKingdomLaunched(Consumer<String> kingdomIdConsumer) {
        appInputHandler.onKingdomLaunched(kingdomIdConsumer);
    }

    @Override
    public void onKingdomStopped(Consumer<String> kingdomIdConsumer) {
        appInputHandler.onKingdomStopped(kingdomIdConsumer);
    }

    @Override
    public void onBattleLaunched(Consumer<BattleConfig> battleConfigConsumer) {
        appInputHandler.onBattleLaunched(battleConfigConsumer);
    }

    @Override
    public void onBattleStopped(Consumer<Integer> battleIdConsumer) {
        appInputHandler.onBattleStopped(battleIdConsumer);
    }

    @Override
    public void disableInputHandling() {
        appInputHandler.disableInputHandling();
    }
}
