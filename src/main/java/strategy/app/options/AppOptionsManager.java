package strategy.app.options;

import strategy.battle.BattleConfig;
import strategy.option.Option;

import java.util.Map;
import java.util.function.Consumer;

public interface AppOptionsManager {
	void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer);
	void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer);
	void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer);
	void addBattleStoppedListener(Consumer<Integer> battleIdConsumer);
	Map<String, Option> getManagedOptions();
}
