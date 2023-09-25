package strategy.provider;

import lombok.AllArgsConstructor;
import strategy.buffer.Buffer;
import strategy.provider.battle.SpeakingBattleIdProvider;
import strategy.provider.battle.SpeakingBufferBattleIdProvider;
import strategy.provider.kingdom.SpeakingBufferKingdomIdProvider;
import strategy.provider.kingdom.SpeakingKingdomIdProvider;

@AllArgsConstructor
public class BufferedProvidersCreator implements ProvidersCreator {

	private final Buffer<String> providersBuffer;

	@Override
	public SpeakingBattleIdProvider createSpeakingBattleIdProvider() {
		return new SpeakingBufferBattleIdProvider(providersBuffer);
	}

	@Override
	public SpeakingKingdomIdProvider createSpeakingKingdomIdProvider() {
		return new SpeakingBufferKingdomIdProvider(providersBuffer);
	}
}
