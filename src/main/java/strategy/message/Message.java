package strategy.message;

public class Message {

	final String content;

	public Message(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}
}
