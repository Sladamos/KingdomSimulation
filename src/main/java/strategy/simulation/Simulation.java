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
import strategy.simulation.api.SimulationAPI;
import strategy.simulation.api.SimulationAPIImpl;
import strategy.util.ProtectedRunnableExecutorService;
import strategy.util.ProtectedThread;

public class Simulation {

    private final SimulationAPI simulationAPI;

    public Simulation() {
        simulationAPI = new SimulationAPIImpl();
    }

    //TODO:
    // create options creator -> here bind events for options manager
    // bind input logger
    // ProvidersCreator(may be speaking or not) and also event in options executioner
    // OptionsCommunicatorCreator -> it creates OptionsCommunicator() or ConsoleOptionsCommunicator(...., options executioner) (but its not necessary to create it)
    // optionsCommunicateCreator - use setters in providers creator

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
        ProtectedRunnableExecutorService.setErrorHandler(errorHandler);
        GUI gui = initializeGUIFromConfig();
        App app = new AppImpl(gui.getAppController(), gui.getAppCommunicator(), gui.getAppOptionsManager());
        app.bindErrorsSender(errorHandler);
        errorHandler.runInErrorHandler(() -> simulationMethod(app));
    }

    private GUI initializeGUIFromConfig() {
        GUIInitializer guiInitializer = new GUIInitializerImpl();
        FileJsonLoader guiConfigLoader = new FileJsonLoaderImpl();
        guiConfigLoader.setFileName("gui.json");
        return guiInitializer.initializeGUI(guiConfigLoader);
    }

    private void simulationMethod(App app) {
        BattleOperatorCreator battleOperatorCreator = new BattleOperatorCreatorImpl();
        AppInitializer appInitializer = new AppInitializerFromFile(battleOperatorCreator);
        SimulationInitializer simulationInitializer = appInitializer.createSimulationInitializer();
        simulationInitializer.initializeSimulation(app, simulationAPI);
    }
}
