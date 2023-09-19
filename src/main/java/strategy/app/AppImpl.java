package strategy.app;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

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
}
