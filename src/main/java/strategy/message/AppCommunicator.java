package strategy.message;

public interface AppCommunicator {
	void bindErrorsSender(MessagesSender<StringMessage> errorsSender);
	void bindBattleSender(MessagesSender<StringMessage> battleSender);
	void bindKingdomSender(MessagesSender<JSONMessage> kingdomSender);
	void receiveErrorMessage(StringMessage message);
	void receiveMessageFromBattle(StringMessage message);
	void receiveMessageFromKingdom(JSONMessage message);
}
