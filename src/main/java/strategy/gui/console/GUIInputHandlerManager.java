package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.app.AppInputHandlerManager;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.option.OptionsExecutioner;
import strategy.option.OptionsExecutionerImpl;

import java.util.function.Consumer;

public class GUIInputHandlerManager implements AppInputHandlerManager {

    private final OneArgEvent<String> kingdomLaunched;

    private final OneArgEvent<String> kingdomStopped;

    private final OneArgEvent<BattleConfig> battleLaunched;

    private final OneArgEvent<Integer> battleStopped;

    private final AppInputHandler inputHandler;

    private final OptionsExecutioner optionsExecutioner;

    private boolean isLaunched;

    public GUIInputHandlerManager(AppInputHandler inputHandler) {
        this.inputHandler = inputHandler;
        isLaunched = false;
        kingdomLaunched = new OneArgEventImpl<>();
        kingdomStopped = new OneArgEventImpl<>();
        battleLaunched = new OneArgEventImpl<>();
        battleStopped = new OneArgEventImpl<>();
        optionsExecutioner = new OptionsExecutionerImpl();
        inputHandler.addInputHandledListener((input) -> optionsExecutioner.getOptionsBuffer().addItem(input));
        initializeExecutionerWithOptions();
    }

    private void initializeExecutionerWithOptions() {
    }

    @Override
    public synchronized void enableInputHandling() {
        optionsExecutioner.getOptionsBuffer().enableAcceptingItems();
        inputHandler.enableInputHandling();
        optionsExecutioner.enableExecuting();
        isLaunched = true;
    }

    @Override
    public void addInputHandledListener(Consumer<String> listener) {
        inputHandler.addInputHandledListener(listener);
    }

    @Override
    public void onKingdomLaunched(Consumer<String> kingdomIdConsumer) {
        kingdomLaunched.addListener(kingdomIdConsumer);
    }

    @Override
    public void onKingdomStopped(Consumer<String> kingdomIdConsumer) {
        kingdomStopped.addListener(kingdomIdConsumer);
    }

    @Override
    public void onBattleLaunched(Consumer<BattleConfig> battleConfigConsumer) {
        battleLaunched.addListener(battleConfigConsumer);
    }

    @Override
    public void onBattleStopped(Consumer<Integer> battleIdConsumer) {
        battleStopped.addListener(battleIdConsumer);
    }

    @Override
    public synchronized void disableInputHandling() {
        optionsExecutioner.disableExecuting();
        optionsExecutioner.getOptionsBuffer().disableAcceptingItems();
        inputHandler.disableInputHandling();
        isLaunched = false;
        notifyAll();
    }

    @Override
    public synchronized void waitOnAppClose() {
        while(isLaunched) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

}
