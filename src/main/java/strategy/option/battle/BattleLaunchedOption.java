package strategy.option.battle;

import strategy.battle.BattleConfig;
import strategy.option.NamedOption;

import java.util.function.Consumer;

public interface BattleLaunchedOption extends NamedOption {
    void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer);
}
