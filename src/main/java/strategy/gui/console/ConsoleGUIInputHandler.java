package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

import java.util.Scanner;
import java.util.function.Consumer;

public class ConsoleGUIInputHandler implements AppInputHandler {

    private final OneArgEvent<String> kingdomLaunched;

    private final OneArgEvent<String> kingdomStopped;

    private final OneArgEvent<BattleConfig> battleLaunched;

    private final OneArgEvent<Integer> battleStopped;

    private boolean isLaunched;

    public ConsoleGUIInputHandler() {
        isLaunched = false;
        kingdomLaunched = new OneArgEventImpl<>();
        kingdomStopped = new OneArgEventImpl<>();
        battleLaunched = new OneArgEventImpl<>();
        battleStopped = new OneArgEventImpl<>();
    }

    @Override
    public synchronized void enableInputHandling() {
        isLaunched = true;
        //run in another thread
        Scanner scanner = new Scanner(System.in);
        while (isLaunched) {
            //displayOptions -- input handler messages receiver in AppCommunicator should be binded
            while (isLaunched && !scanner.hasNext()) {
                Thread.sleep(200);
            }
            //read line from scanner
            //run in error handling layer both:
                //get option (what if incorrect optionId) -> basic Error (how to catch it?)
                //optionsExecutioner.executeOption()
        }
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
