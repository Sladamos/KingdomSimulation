package strategy.option.kingdom;

import strategy.option.Option;

import java.util.function.Consumer;

public interface KingdomDisabledOption extends Option {
	void addKingdomDisabledListener(Consumer<String> kingdomIdConsumer);
}
