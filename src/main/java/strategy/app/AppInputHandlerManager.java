package strategy.app;

import strategy.battle.BattleConfig;

import java.util.function.Consumer;

public interface AppInputHandlerManager extends AppInputHandler {
    void onKingdomLaunched(Consumer<String> kingdomIdConsumer);
    void onKingdomStopped(Consumer<String> kingdomIdConsumer);
    void onBattleLaunched(Consumer<BattleConfig> battleConfigConsumer);
    void onBattleStopped(Consumer<Integer> battleIdConsumer);
    void waitOnAppClose();
}
