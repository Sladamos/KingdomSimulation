package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.app.AppInputHandlerManager;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.function.Consumer;

public class GUIInputHandlerManager implements AppInputHandlerManager {

    private final OneArgEvent<String> kingdomLaunched;

    private final OneArgEvent<String> kingdomStopped;

    private final OneArgEvent<BattleConfig> battleLaunched;

    private final OneArgEvent<Integer> battleStopped;

    private final AppInputHandler inputHandler;

    private boolean isLaunched;

    public GUIInputHandlerManager(AppInputHandler inputHandler) {
        this.inputHandler = inputHandler;
        isLaunched = false;
        kingdomLaunched = new OneArgEventImpl<>();
        kingdomStopped = new OneArgEventImpl<>();
        battleLaunched = new OneArgEventImpl<>();
        battleStopped = new OneArgEventImpl<>();
    }

    @Override
    public synchronized void enableInputHandling() {
        inputHandler.enableInputHandling();
        isLaunched = true;
        //run in error handling layer both:
        //get option (what if incorrect optionId) -> basic Error (how to catch it?)
        //optionsExecutioner.executeOption()

    }

    @Override
    public void addInputHandledListener(Consumer<String> listener) {

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
