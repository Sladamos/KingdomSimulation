package strategy.message;

public interface AppCommunicator {
	void bindErrorsNotifier(MessagesNotifier<StringMessage> errorsNotifier);
	void bindBattleNotifier(MessagesNotifier<StringMessage> battleNotifier);
	void bindKingdomNotifier(MessagesNotifier<JSONMessage> kingdomNotifier);
	void receiveErrorMessage(StringMessage message);
	void receiveMessageFromBattle(StringMessage message);
	void receiveMessageFromKingdom(JSONMessage message);
}
