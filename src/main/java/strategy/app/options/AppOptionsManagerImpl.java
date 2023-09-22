package strategy.app.options;

import strategy.battle.BattleConfig;
import strategy.events.noargsevent.NoArgEvent;
import strategy.events.noargsevent.NoArgEventImpl;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.option.Option;
import strategy.option.kingdom.KingdomStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AppOptionsManagerImpl implements ModificableAppOptionsManager {

	private final OneArgEvent<String> kingdomLaunched;

	private final OneArgEvent<String> kingdomStopped;

	private final OneArgEvent<BattleConfig> battleLaunched;

	private final OneArgEvent<Integer> battleStopped;

	private final NoArgEvent appDisabled;

	private final Map<String, Option> managedOptions;

	public AppOptionsManagerImpl() {
		kingdomLaunched = new OneArgEventImpl<>();
		kingdomStopped = new OneArgEventImpl<>();
		battleLaunched = new OneArgEventImpl<>();
		battleStopped = new OneArgEventImpl<>();
		appDisabled = new NoArgEventImpl();
		managedOptions = new HashMap<>();
	}

	@Override
	public void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption) {
		managedOptions.put("Launch kingdom", kingdomLaunchedOption);
		kingdomLaunchedOption.addKingdomLaunchedListener(kingdomLaunched::invoke);
	}

	@Override
	public void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption) {
		managedOptions.put("Stop kingdom", kingdomStoppedOption);
		kingdomStoppedOption.addKingdomStoppedListener(kingdomStopped::invoke);
	}

	@Override
	public void setAppDisabledOption(Option appDisabledOption) {
		managedOptions.put("Exit", appDisabledOption);
	}

	@Override
	public void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer) {
		kingdomLaunched.addListener(kingdomIdConsumer);
	}

	@Override
	public void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer) {
		kingdomStopped.addListener(kingdomIdConsumer);
	}

	@Override
	public void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer) {
		battleLaunched.addListener(battleConfigConsumer);
	}

	@Override
	public void addBattleStoppedListener(Consumer<Integer> battleIdConsumer) {
		battleStopped.addListener(battleIdConsumer);
	}

	@Override
	public Map<String, Option> getManagedOptions() {
		return managedOptions;
	}
}
