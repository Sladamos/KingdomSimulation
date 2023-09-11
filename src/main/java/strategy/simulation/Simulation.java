package strategy.simulation;

import strategy.battle.operator.BattleOperatorCreator;
import strategy.battle.operator.BattleOperatorCreatorImpl;
import strategy.initializer.SimulationInitializer;
import strategy.initializer.app.AppInitializer;
import strategy.initializer.app.AppInitializerFromFile;
import strategy.app.AppCommunicator;
import strategy.app.AppCommunicatorImpl;
import strategy.message.receiver.ConsoleMessagesReceiver;

public class Simulation {

    //TODO: Kingdoms should have unique id

    //TODO: add enum to simulation type and to gui type

    //TODO: error handling layer, when critical app error catched display error and shut down app , when app error display it only
    //  Also handle exception: SafeDisable(); -> then display something and end app


    //TODO: before simulation initialize there should be created all gui (ApplicationMessenger)
    //  for example in battlereceiver create StringProperty, and change it when new messsage will come.

    /*TODO gui:
        two panels with names of materials and count of each one (consider it!):
        three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
        one button -> start battle
        mid panel -> battle results // or create new window
        button to clear panel
        layer for error handling -> both for console and gui
     */

    public static void main(String[] args) {

        //TODO gui and messengerInitializer (gui / console)
        //TODO:
        //  create error handling layer. it should have method run (runnable), which executes some function
        //  create app communicator from guiInitializer
        Simulation simulation = new Simulation();
        simulation.start();
        //createErrorLayer()
        //bind console communicator to error layer
        //errorLayer->execute(() -> gui = createGui());
        //unbind console communicator
        //gui.getAppCommunicator().bindErrorsSender(errorLayer);
        //errorLayer->execute(simulationMethod());
    }

    private void start() {
        AppCommunicator appCommunicator = new AppCommunicatorImpl(new ConsoleMessagesReceiver<>(),
                new ConsoleMessagesReceiver<>(), new ConsoleMessagesReceiver<>());
        BattleOperatorCreator battleOperatorCreator = new BattleOperatorCreatorImpl();
        AppInitializer appInitializer = new AppInitializerFromFile(battleOperatorCreator);
        SimulationInitializer simulationInitializer = appInitializer.createSimulationInitializer();
        simulationInitializer.initializeSimulation(appCommunicator);
    }
}
