package strategy.simulation;

import strategy.app.App;
import strategy.app.AppImpl;
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
import strategy.util.ProtectedThread;

public class Simulation {
    //TODO:
    // remove thread from executioner -> launch it in manager
    // also create input handler launcher -> in console for example run in new thread
    // exit option should be specified by APP
    // options initializer class in gui -> add options to options executioner (or map!)
    // maybe method add New Options
    // options displaying
    // 1. find option creator 2. create option 3. execute option 4. display possible options creators
    // launchKingdomCreator: createOption(buffer) -> //get kingdom Id
    // for example LaunchKingdomOption -> execute : kingdomLaunched.invoke();

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
        ErrorHandler errorHandler = new ErrorHandlerImpl();
        ProtectedThread.setErrorHandler(errorHandler);
        GUIInitializer guiInitializer = new GUIInitializerImpl();
        FileJsonLoader guiConfigLoader = new FileJsonLoaderImpl();
        guiConfigLoader.setFileName("gui.json");
        GUI gui = guiInitializer.initializeGUI(guiConfigLoader);
        App app = new AppImpl(gui.getAppInputHandler(), gui.getAppCommunicator());
        app.bindErrorsSender(errorHandler);
        errorHandler.runInErrorHandler(() -> simulationMethod(app));
    }

    private void simulationMethod(App app) {
        BattleOperatorCreator battleOperatorCreator = new BattleOperatorCreatorImpl();
        AppInitializer appInitializer = new AppInitializerFromFile(battleOperatorCreator);
        SimulationInitializer simulationInitializer = appInitializer.createSimulationInitializer();
        simulationInitializer.initializeSimulation(app, new SimulationExecutionerImpl());
    }
}
