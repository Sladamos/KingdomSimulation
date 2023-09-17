package strategy.battle.operator;

import lombok.AllArgsConstructor;
import strategy.battle.Battle;
import strategy.battle.SimpleBattle;
import strategy.battle.creator.BattleCreator;
import strategy.battle.creator.SimpleBattleCreator;
import strategy.battle.operator.BattleOperator;
import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class BasicBattleOperator implements BattleOperator {

    private final BattleCreator battleCreator;

    private final ExecutorService executorService;

    public BasicBattleOperator() {
        battleCreator = new SimpleBattleCreator();
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public Battle createBattle(Kingdom firstKingdom, Kingdom secondKingdom) {
        return battleCreator.createBattle(firstKingdom, secondKingdom);
    }

    @Override
    public synchronized void launchBattle(Battle battle) {
        executorService.execute(battle);
    }

    @Override
    public synchronized void waitForBattlesEnd() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception ignored) {
        }
    }
}
