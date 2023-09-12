package strategy.app;

import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;
import strategy.message.StringMessage;

public interface AppCommunicator {
	void bindErrorsSender(MessagesSender<JSONMessage> errorsSender);
	void bindBattleSender(MessagesSender<StringMessage> battleSender);
	void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender);
	void receiveErrorMessage(JSONMessage message);
	void receiveMessageFromBattle(StringMessage message);
	void receiveMessageFromKingdom(JSONMessage message);
}
