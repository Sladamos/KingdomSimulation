package strategy.message.receiver;

public class ConsoleMessagesReceiver implements MessagesReceiver {
	@Override
	public void receiveMessage(String message) {
		System.out.println(message);
	}
}
