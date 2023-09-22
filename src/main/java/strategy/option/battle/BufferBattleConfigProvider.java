package strategy.option.battle;

import strategy.battle.BattleConfig;
import strategy.battle.BattleConfigImpl;
import strategy.buffer.Buffer;
import strategy.error.BasicAppError;

public class BufferBattleConfigProvider implements BattleConfigProvider {

    private final Buffer<String> optionsBuffer;

    public BufferBattleConfigProvider(Buffer<String> optionsBuffer) {
        this.optionsBuffer = optionsBuffer;
    }

    @Override
    public BattleConfig getBattleConfig() {
        try {
            Integer battleId = Integer.valueOf(optionsBuffer.getItem());
            String firstKingdomId = optionsBuffer.getItem();
            String secondKingdomId = optionsBuffer.getItem();
            return new BattleConfigImpl(battleId, firstKingdomId, secondKingdomId);
        } catch (NumberFormatException err) {
            throw new BasicAppError("Battle id must be a number.");
        }
    }
}
