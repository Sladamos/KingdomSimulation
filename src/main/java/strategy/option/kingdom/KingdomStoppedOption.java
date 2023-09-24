package strategy.option.kingdom;

import strategy.option.NamedOption;

import java.util.function.Consumer;

public interface KingdomStoppedOption extends NamedOption {
	void addKingdomStoppedListener(Consumer<String> kingdomIdConsumer);
}
