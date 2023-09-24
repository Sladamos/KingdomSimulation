package strategy.app.options;

import lombok.Getter;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.option.NamedOption;
import strategy.option.battle.BattleLaunchedOption;
import strategy.option.battle.BattleStoppedOption;
import strategy.option.kingdom.KingdomLaunchedOption;
import strategy.option.kingdom.KingdomStoppedOption;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AppOptionsManagerImpl implements ModificableAppOptionsManager {

	private final OneArgEvent<String> kingdomLaunched;

	private final OneArgEvent<String> kingdomStopped;

	private final OneArgEvent<BattleConfig> battleLaunched;

	private final OneArgEvent<Integer> battleStopped;

	@Getter
	private final Map<String, NamedOption> managedOptions;

	public AppOptionsManagerImpl() {
		kingdomLaunched = new OneArgEventImpl<>();
		kingdomStopped = new OneArgEventImpl<>();
		battleLaunched = new OneArgEventImpl<>();
		battleStopped = new OneArgEventImpl<>();
		managedOptions = new HashMap<>();
	}

	@Override
	public void setKingdomLaunchedOption(KingdomLaunchedOption kingdomLaunchedOption) {
		managedOptions.put(kingdomLaunchedOption.getName(), kingdomLaunchedOption);
		kingdomLaunchedOption.addKingdomLaunchedListener(kingdomLaunched::invoke);
	}

	@Override
	public void setKingdomStoppedOption(KingdomStoppedOption kingdomStoppedOption) {
		managedOptions.put(kingdomStoppedOption.getName(), kingdomStoppedOption);
		kingdomStoppedOption.addKingdomStoppedListener(kingdomStopped::invoke);
	}

	@Override
	public void setBattleLaunchedOption(BattleLaunchedOption battleLaunchedOption) {
		managedOptions.put(battleLaunchedOption.getName(), battleLaunchedOption);
		battleLaunchedOption.addBattleLaunchedListener(battleLaunched::invoke);
	}

	@Override
	public void setBattleStoppedOption(BattleStoppedOption battleStoppedOption) {
		managedOptions.put(battleStoppedOption.getName(), battleStoppedOption);
		battleStoppedOption.addBattleStoppedListener(battleStopped::invoke);
	}

	@Override
	public void setAppDisabledOption(NamedOption appDisabledOption) {
		managedOptions.put(appDisabledOption.getName(), appDisabledOption);
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
}
