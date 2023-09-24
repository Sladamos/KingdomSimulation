package strategy.provider.battle;

import strategy.buffer.Buffer;
import strategy.error.BasicAppError;

public class BufferBattleIdProvider implements BattleIdProvider {

    private final Buffer<String> optionsBuffer;

    public BufferBattleIdProvider(Buffer<String> optionsBuffer) {
        this.optionsBuffer = optionsBuffer;
    }

    @Override
    public Integer getBattleId() {
        try {
            return Integer.valueOf(optionsBuffer.getItem());
        } catch (NumberFormatException err) {
            throw new BasicAppError("Battle id must be a number.");
        }
    }
}
