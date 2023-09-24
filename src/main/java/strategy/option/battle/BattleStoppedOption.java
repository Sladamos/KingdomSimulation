package strategy.option.battle;

import strategy.option.NamedOption;

import java.util.function.Consumer;

public interface BattleStoppedOption extends NamedOption {
    void addBattleStoppedListener(Consumer<Integer> battleIdConsumer);
}
