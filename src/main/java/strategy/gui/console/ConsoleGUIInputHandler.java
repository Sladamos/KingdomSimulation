package strategy.gui.console;

import strategy.app.AppInputHandler;
import strategy.battle.BattleConfig;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;

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
    public void enableInputHandling() {
//        isLaunched = true;
//        createScanner;
//        while (isLaunched) {
//            scan option name;
//            execute option
//
//        }
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

}
