package strategy.app;

import strategy.message.JSONMessage;
import strategy.message.sender.MessagesSender;
import strategy.message.StringMessage;

public interface AppCommunicator {
	void bindErrorsSender(MessagesSender<StringMessage> errorsSender);
	void bindBattleSender(MessagesSender<StringMessage> battleSender);
	void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender);
	void receiveErrorMessage(StringMessage message);
	void receiveMessageFromBattle(StringMessage message);
	void receiveMessageFromKingdom(JSONMessage message);
}
