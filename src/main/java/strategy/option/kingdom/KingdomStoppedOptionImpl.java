package strategy.option.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class KingdomStoppedOptionImpl implements KingdomStoppedOption {

	private final OneArgEvent<String> kingdomStopped;

	private final KingdomIdProvider kingdomIdProvider;

	public KingdomStoppedOptionImpl(KingdomIdProvider kingdomIdProvider) {
		this.kingdomIdProvider = kingdomIdProvider;
		kingdomStopped = new OneArgEventImpl<>();
	}

	@Override
	public void execute() {
		String kingdomId = kingdomIdProvider.getKingdomId();
		kingdomStopped.invoke(kingdomId);
	}

	@Override
	public void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer) {
		kingdomStopped.addListener(kingdomIdConsumer);
	}
}
