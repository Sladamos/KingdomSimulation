package strategy.kingdom.launcher;

import strategy.kingdom.Kingdom;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class KingdomLauncherImpl implements KingdomLauncher {

    private final ExecutorService executor;

    private final Map<Kingdom, List<Future<?>>> registeredKingdoms;

    public KingdomLauncherImpl() {
        executor = Executors.newCachedThreadPool();
        registeredKingdoms = new HashMap<>();
    }

    @Override
    public void launchKingdom(Kingdom kingdom, long miliseconds) {
        Future<?> kingdomFuture = executor.submit(() -> simulateKingdomDevelop(kingdom, miliseconds));
        registerFuture(kingdom, kingdomFuture);
    }

    private void registerFuture(Kingdom kingdom, Future<?> kingdomFuture) {
        if(registeredKingdoms.containsKey(kingdom)) {
            registeredKingdoms.get(kingdom).add(kingdomFuture);
        } else {
            List<Future<?>> kingdomFutures = new ArrayList<>(Collections.singletonList(kingdomFuture));
            registeredKingdoms.put(kingdom, kingdomFutures);
        }
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
        if(registeredKingdoms.containsKey(kingdom)) {
            var battleFutures = registeredKingdoms.get(kingdom);
            for(var future : battleFutures) {
                future.cancel(true);
            }
            registeredKingdoms.remove(kingdom);
        }
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
