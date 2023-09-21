package strategy.gui;

import strategy.app.AppInputHandler;
import strategy.app.AppInputHandlerManager;
import strategy.option.OptionsExecutioner;

import java.util.function.Consumer;

public class GUIInputHandlerManager implements AppInputHandlerManager {

    private final AppInputHandler inputHandler;

    private final OptionsExecutioner optionsExecutioner;

    private boolean isLaunched;

    public GUIInputHandlerManager(AppInputHandler inputHandler, OptionsExecutioner optionsExecutioner) {
        this.inputHandler = inputHandler;
        this.optionsExecutioner = optionsExecutioner;
        isLaunched = false;
    }

    @Override
    public synchronized void enableInputHandling() {
        //optionsExecutioner.getOptionsBuffer().enableAcceptingItems();
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
        //optionsExecutioner.getOptionsBuffer().disableAcceptingItems();
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
