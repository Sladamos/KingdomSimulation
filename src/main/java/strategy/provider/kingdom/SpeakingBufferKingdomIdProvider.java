package strategy.provider.kingdom;

import strategy.buffer.Buffer;
import strategy.events.noargsevent.NoArgEvent;
import strategy.events.noargsevent.NoArgEventImpl;

public class SpeakingBufferKingdomIdProvider implements KingdomIdProvider {

    private final Buffer<String> optionsBuffer;

    private final NoArgEvent needId;

    public SpeakingBufferKingdomIdProvider(Buffer<String> optionsBuffer) {
        this.optionsBuffer = optionsBuffer;
        needId = new NoArgEventImpl();
    }

    @Override
    public String getKingdomId() {
        needId.invoke();
        return optionsBuffer.getItem();
    }

    @Override
    public void addListener(Runnable listener) {
        needId.addListener(listener);
    }
}

