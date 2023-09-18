package strategy.kingdom.launcher;

import strategy.kingdom.Kingdom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KingdomLauncherImpl implements KingdomLauncher {

    private final ExecutorService executor;

    public KingdomLauncherImpl() {
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void launchKingdom(Kingdom kingdom, long miliseconds) {
        executor.submit(() -> simulateKingdomDevelop(kingdom, miliseconds));
    }

    @Override
    public void waitForDevelopingEnd() {
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void stopKingdom(Kingdom kingdom) {

    }

    private void simulateKingdomDevelop(Kingdom kingdom, long sleep) {
        kingdom.run();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        kingdom.terminate();
    }
}
