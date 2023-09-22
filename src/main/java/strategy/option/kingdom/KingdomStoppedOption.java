package strategy.option.kingdom;

import strategy.option.Option;

import java.util.function.Consumer;

public interface KingdomStoppedOption extends Option {
	void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer);
}
