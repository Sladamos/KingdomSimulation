package strategy.option.kingdom;

import strategy.option.Option;

import java.util.function.Consumer;

public interface KingdomLaunchedOption extends Option {
	void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer);
}
