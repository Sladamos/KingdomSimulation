package strategy.option.battle;

import strategy.option.Option;

import java.util.function.Consumer;

public interface BattleStoppedOption extends Option {
    void addBattleStoppedListener(Consumer<Integer> battleIdConsumer);
}
