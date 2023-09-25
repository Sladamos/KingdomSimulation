package strategy.provider.battle;

import strategy.buffer.Buffer;
import strategy.error.BasicAppError;
import strategy.events.noargsevent.NoArgEvent;
import strategy.events.noargsevent.NoArgEventImpl;

public class SpeakingBufferBattleIdProvider implements SpeakingBattleIdProvider {

    private final Buffer<String> optionsBuffer;

    private final NoArgEvent needId;

    public SpeakingBufferBattleIdProvider(Buffer<String> optionsBuffer) {
        this.optionsBuffer = optionsBuffer;
        needId = new NoArgEventImpl();
    }

    @Override
    public Integer getBattleId() {
        try {
            needId.invoke();
            return Integer.valueOf(optionsBuffer.getItem());
        } catch (NumberFormatException err) {
            throw new BasicAppError("Battle id must be a number.");
        }
    }

    @Override
    public void addListener(Runnable listener) {
        needId.addListener(listener);
    }
}