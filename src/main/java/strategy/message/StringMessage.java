package strategy.message;

import lombok.Getter;

public class StringMessage implements Message<String> {

	@Getter
	private final String content;

	public StringMessage(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return content;
	}
}
