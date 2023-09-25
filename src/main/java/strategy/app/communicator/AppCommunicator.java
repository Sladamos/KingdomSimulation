package strategy.app.communicator;

import strategy.message.JSONMessage;
import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

public interface AppCommunicator {
	void bindErrorsSender(MessagesSender<JSONMessage> errorsSender);
	void bindBattleSender(MessagesSender<StringMessage> battleSender);
	void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender);
	void receiveErrorMessage(JSONMessage message);
	void receiveMessageFromBattle(StringMessage message);
	void receiveMessageFromKingdom(JSONMessage message);
}
