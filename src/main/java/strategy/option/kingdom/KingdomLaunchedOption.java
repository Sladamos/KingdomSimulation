package strategy.option.kingdom;

import strategy.option.NamedOption;

import java.util.function.Consumer;

public interface KingdomLaunchedOption extends NamedOption {
	void addKingdomLaunchedListener(Consumer<String> kingdomIdConsumer);
}
