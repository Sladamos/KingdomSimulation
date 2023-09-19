package strategy.app;

import java.util.function.Consumer;

public interface AppInputHandler {
    void enableInputHandling();
    void onKingdomLaunched(Consumer<String> kingdomIdConsumer);
    void onKingdomStopped(Consumer<String> kingdomIdConsumer);
    void onBattleLaunched(Consumer<Integer> battleIdConsumer);
    void onBattleStopped(Consumer<Integer> battleIdConsumer);
}
