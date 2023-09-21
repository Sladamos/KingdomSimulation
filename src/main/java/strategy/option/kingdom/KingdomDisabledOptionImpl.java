package strategy.option.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class KingdomDisabledOptionImpl implements KingdomDisabledOption {

	private final OneArgEvent<String> kingdomDisabled;

	private final KingdomIdProvider kingdomIdProvider;

	public KingdomDisabledOptionImpl(KingdomIdProvider kingdomIdProvider) {
		this.kingdomIdProvider = kingdomIdProvider;
		kingdomDisabled = new OneArgEventImpl<>();
	}

	@Override
	public void execute() {
		String kingdomId = kingdomIdProvider.getKingdomId();
		kingdomDisabled.invoke(kingdomId);
	}

	@Override
	public void addKingdomDisabledListener(Consumer<String> kingdomIdConsumer) {
		kingdomDisabled.addListener(kingdomIdConsumer);
	}
}
