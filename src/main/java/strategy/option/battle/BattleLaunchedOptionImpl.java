package strategy.option.battle;

import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class BattleLaunchedOptionImpl implements BattleLaunchedOption {

    private final OneArgEvent<BattleConfig> battleLaunched;

    private final BattleConfigProvider battleConfigProvider;

    public BattleLaunchedOptionImpl(BattleConfigProvider battleIdProvider) {
        this.battleConfigProvider = battleIdProvider;
        battleLaunched = new OneArgEventImpl<>();
    }

    @Override
    public void execute() {
        BattleConfig battleConfig = battleConfigProvider.getBattleConfig();
        battleLaunched.invoke(battleConfig);
    }

    @Override
    public void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer) {
        battleLaunched.addListener(battleConfigConsumer);
    }
}
