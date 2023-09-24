package strategy.option.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.function.Consumer;

public class KingdomLaunchedOptionImpl implements KingdomLaunchedOption {
	
	private final OneArgEvent<String> kingdomLaunched;

	private final KingdomIdProvider kingdomIdProvider;

	public KingdomLaunchedOptionImpl(KingdomIdProvider kingdomIdProvider) {
		this.kingdomIdProvider = kingdomIdProvider;
		kingdomLaunched = new OneArgEventImpl<>();
	}

	@Override
	public void execute() {
		String kingdomId = kingdomIdProvider.getKingdomId();
		kingdomLaunched.invoke(kingdomId);
	}

	@Override
	public void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer) {
		kingdomLaunched.addListener(kingdomIdConsumer);
	}
}
