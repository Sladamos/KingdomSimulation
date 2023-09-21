package strategy.option.kingdom;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.option.Option;

import java.util.function.Consumer;

public class KingdomLaunchedOptionImpl implements KingdomLaunchedOption {
	
	private final OneArgEvent<String> kingdomLaunched;

	public KingdomLaunchedOptionImpl() {
		kingdomLaunched = new OneArgEventImpl<>();
	}

	@Override
	public void execute() {
		//passIdCreatorInConstructor(fromInputBuffer)
		//getIdFromCreator
		kingdomLaunched.invoke("");
	}

	@Override
	public void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer) {
		kingdomLaunched.addListener(kingdomIdConsumer);
	}
}
