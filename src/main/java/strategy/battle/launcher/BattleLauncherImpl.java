package strategy.battle.launcher;

import strategy.battle.Battle;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BattleLauncherImpl implements BattleLauncher {

    private final ExecutorService executor;

	private final Map<Battle, List<Future<?>>> registeredBattles;

	public BattleLauncherImpl() {
		executor = Executors.newCachedThreadPool();
		registeredBattles = new HashMap<>();
	}

	@Override
	public synchronized void launchBattle(Battle battle) {
		Future<?> battleFuture = executor.submit(battle);
		registerFuture(battle, battleFuture);
	}

	private void registerFuture(Battle battle, Future<?> battleFuture) {
		if(registeredBattles.containsKey(battle)) {
			registeredBattles.get(battle).add(battleFuture);
		} else {
			List<Future<?>> battleFutures = new ArrayList<>(Collections.singletonList(battleFuture));
			registeredBattles.put(battle, battleFutures);
		}
	}

	@Override
	public synchronized void waitForBattlesEnd() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (Exception ignored) {
		}
	}

	@Override
	public synchronized void stopBattle(Battle battle) {
		if(registeredBattles.containsKey(battle)) {
			var battleFutures = registeredBattles.get(battle);
			for(var future : battleFutures) {
				future.cancel(true);
			}
			registeredBattles.remove(battle);
		}
	}
}
