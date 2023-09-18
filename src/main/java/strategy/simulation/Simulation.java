package strategy.simulation;

import strategy.app.AppCommunicator;
import strategy.battle.operator.BattleOperatorCreator;
import strategy.battle.operator.BattleOperatorCreatorImpl;
import strategy.error.ErrorHandler;
import strategy.error.ErrorHandlerImpl;
import strategy.gui.GUI;
import strategy.initializer.app.AppInitializer;
import strategy.initializer.app.AppInitializerFromFile;
import strategy.initializer.gui.GUIInitializer;
import strategy.initializer.gui.GUIInitializerImpl;
import strategy.initializer.simulation.SimulationInitializer;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;
import strategy.simulation.executioner.SimulationExecutionerImpl;

public class Simulation {
    // TODO: think about automatic vs manual simulation -> on initialize simulation pass some object
    //  simulationInitializer.initializeSimulation pass app Fasade
    //  in fasade i will have communicator and nullable input handler -> i will bind both of them
    //  then call method wait for simulation end or something like that -> mechanism wait notify!
    //  handle input in runnable thread
    //  if null in manual throw critical exception

    /*TODO gui:
        two panels with names of materials and count of each one (consider it!):
        three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
        one button -> start battle
        mid panel -> battle results // or create new window
        button to clear panel
        layer for error handling -> both for console and gui
     */

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation();
            simulation.start();
        }
         catch (Exception err) {
            System.out.println("UNHANDLED EXCEPTION!");
            System.out.println(err.getMessage());
        }
    }

    private void start() {
        GUIInitializer guiInitializer = new GUIInitializerImpl();
        FileJsonLoader guiConfigLoader = new FileJsonLoaderImpl();
        guiConfigLoader.setFileName("gui.json");
        GUI gui = guiInitializer.initializeGUI(guiConfigLoader);
        AppCommunicator appCommunicator = gui.getAppCommunicator();
        ErrorHandler errorHandler = new ErrorHandlerImpl();
        appCommunicator.bindErrorsSender(errorHandler);
        errorHandler.runInErrorHandler(() -> simulationMethod(appCommunicator));
    }

    private void simulationMethod(AppCommunicator appCommunicator) {
        BattleOperatorCreator battleOperatorCreator = new BattleOperatorCreatorImpl();
        AppInitializer appInitializer = new AppInitializerFromFile(battleOperatorCreator);
        SimulationInitializer simulationInitializer = appInitializer.createSimulationInitializer();
        simulationInitializer.initializeSimulation(appCommunicator, new SimulationExecutionerImpl());
    }
}
