package strategy.option.battle;

import strategy.battle.BattleConfig;
import strategy.option.Option;

import java.util.function.Consumer;

public interface BattleLaunchedOption extends Option {
    void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer);
}
