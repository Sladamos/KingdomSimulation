package strategy.option.kingdom;

import strategy.buffer.Buffer;

public class BufferKingdomIdProvider implements KingdomIdProvider {

	private final Buffer<String> optionsBuffer;

	public BufferKingdomIdProvider(Buffer<String> optionsBuffer) {
		this.optionsBuffer = optionsBuffer;
	}

	@Override
	public String getKingdomId() {
		return optionsBuffer.getItem();
	}
}
