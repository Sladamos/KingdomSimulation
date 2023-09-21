package strategy.app;

import strategy.app.options.AppOptionsManager;
import strategy.battle.BattleConfig;
import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;
import strategy.option.Option;
import strategy.option.kingdom.KingdomLaunchedOption;

import java.util.Map;
import java.util.function.Consumer;

public class AppImpl implements App {
    
    private final AppCommunicator appCommunicator;

    private final AppInputHandlerManager appInputHandlerManager;

    private final AppOptionsManager appOptionsManager;

    public AppImpl(AppInputHandlerManager appInputHandlerManager, AppCommunicator appCommunicator, AppOptionsManager appOptionsManager) {
        this.appInputHandlerManager = appInputHandlerManager;
        this.appCommunicator = appCommunicator;
        this.appOptionsManager = appOptionsManager;
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
    public void waitOnAppClose() {
        appInputHandlerManager.waitOnAppClose();
    }

    @Override
    public void disableInputHandling() {
        appInputHandlerManager.disableInputHandling();
    }

    @Override
    public void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer) {
        appOptionsManager.addKingdomLaunchedListener(kingdomIdConsumer);
    }

    @Override
    public void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer) {
        appOptionsManager.addKingdomStoppedListener(kingdomIdConsumer);
    }

    @Override
    public void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer) {
        appOptionsManager.addBattleLaunchedListener(battleConfigConsumer);
    }

    @Override
    public void addBattleStoppedListener(Consumer<Integer> battleIdConsumer) {
        appOptionsManager.addBattleStoppedListener(battleIdConsumer);
    }

    @Override
    public Map<String, Option> getManagedOptions() {
        return appOptionsManager.getManagedOptions();
    }
}
