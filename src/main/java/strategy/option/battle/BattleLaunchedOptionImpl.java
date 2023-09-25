package strategy.option.battle;

import strategy.battle.BattleConfig;
import strategy.battle.BattleConfigImpl;
import strategy.battle.id.BattleIdGenerator;
import strategy.battle.id.BattleIdGeneratorImpl;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.provider.kingdom.KingdomIdProvider;

import java.util.function.Consumer;

public class BattleLaunchedOptionImpl implements BattleLaunchedOption {

    private final OneArgEvent<BattleConfig> battleLaunched;

    private final KingdomIdProvider kingdomIdProvider;

    private final BattleIdGenerator battleIdGenerator;

    public BattleLaunchedOptionImpl(KingdomIdProvider kingdomIdProvider) {
        this.kingdomIdProvider = kingdomIdProvider;
        battleLaunched = new OneArgEventImpl<>();
        battleIdGenerator = new BattleIdGeneratorImpl();
    }

    @Override
    public void execute() {
        String firstKingdomId = kingdomIdProvider.getKingdomId();
        String secondKingdomId = kingdomIdProvider.getKingdomId();
        Integer battleId = battleIdGenerator.generateId();
        BattleConfig battleConfig = new BattleConfigImpl(battleId, firstKingdomId, secondKingdomId);
        battleLaunched.invoke(battleConfig);
    }

    @Override
    public void addBattleLaunchedListener(Consumer<BattleConfig> battleConfigConsumer) {
        battleLaunched.addListener(battleConfigConsumer);
    }

    @Override
    public String getName() {
        return "Launch battle";
    }
}
