package strategy.gui;

import strategy.app.AppInputHandler;
import strategy.app.AppInputHandlerManager;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.option.OptionsExecutioner;
import strategy.option.OptionsExecutionerImpl;

import java.util.function.Consumer;

public class GUIInputHandlerManager implements AppInputHandlerManager {

    private final AppInputHandler inputHandler;

    private final OptionsExecutioner optionsExecutioner;

    private boolean isLaunched;

    public GUIInputHandlerManager(AppInputHandler inputHandler) {
        this.inputHandler = inputHandler;
        isLaunched = false;
        optionsExecutioner = new OptionsExecutionerImpl();
        inputHandler.addInputHandledListener((input) -> optionsExecutioner.getOptionsBuffer().addItem(input));
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
