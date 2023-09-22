package strategy.option.battle;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class BattleStoppedOptionImpl implements BattleStoppedOption {

    private final OneArgEvent<Integer> battleStopped;

    private final BattleIdProvider battleIdProvider;

    public BattleStoppedOptionImpl(BattleIdProvider battleIdProvider) {
        this.battleIdProvider = battleIdProvider;
        battleStopped = new OneArgEventImpl<>();
    }

    @Override
    public void execute() {
        Integer kingdomId = battleIdProvider.getBattleId();
        battleStopped.invoke(kingdomId);
    }

    @Override
    public void addBattleStoppedListener(Consumer<Integer> battleIdConsumer) {
        battleStopped.addListener(battleIdConsumer);
    }
}
