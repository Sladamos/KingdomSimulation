package strategy.app;

import strategy.battle.BattleConfig;

import java.util.function.Consumer;

public interface AppInputHandlerManager extends AppInputHandler {
    void waitOnAppClose();
}
