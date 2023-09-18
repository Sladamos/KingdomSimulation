package strategy.battle.launcher;

import strategy.battle.Battle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BattleLauncherImpl implements BattleLauncher {

    private final ExecutorService executor;

	public BattleLauncherImpl() {
		executor = Executors.newCachedThreadPool();
	}

	@Override
	public synchronized void launchBattle(Battle battle) {
		executor.execute(battle);
	}

	@Override
	public synchronized void waitForBattlesEnd() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (Exception ignored) {
		}
	}
}
