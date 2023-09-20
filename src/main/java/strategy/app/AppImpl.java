package strategy.app;

import strategy.battle.BattleConfig;
import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

import java.util.function.Consumer;

public class AppImpl implements App {
    
    private final AppCommunicator appCommunicator;

    private final AppInputHandlerManager appInputHandlerManager;

    public AppImpl(AppInputHandlerManager appInputHandlerManager, AppCommunicator appCommunicator) {
        this.appInputHandlerManager = appInputHandlerManager;
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
        appInputHandlerManager.enableInputHandling();
    }

    @Override
    public void addInputHandledListener(Consumer<String> listener) {
        appInputHandlerManager.addInputHandledListener(listener);
    }

    @Override
    public void onKingdomLaunched(Consumer<String> kingdomIdConsumer) {
        appInputHandlerManager.onKingdomLaunched(kingdomIdConsumer);
    }

    @Override
    public void onKingdomStopped(Consumer<String> kingdomIdConsumer) {
        appInputHandlerManager.onKingdomStopped(kingdomIdConsumer);
    }

    @Override
    public void onBattleLaunched(Consumer<BattleConfig> battleConfigConsumer) {
        appInputHandlerManager.onBattleLaunched(battleConfigConsumer);
    }

    @Override
    public void onBattleStopped(Consumer<Integer> battleIdConsumer) {
        appInputHandlerManager.onBattleStopped(battleIdConsumer);
    }

    @Override
    public void waitOnAppClose() {
        appInputHandlerManager.waitOnAppClose();
    }

    @Override
    public void disableInputHandling() {
        appInputHandlerManager.disableInputHandling();
    }
}
