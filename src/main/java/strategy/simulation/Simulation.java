package strategy.simulation;

import strategy.app.AppCommunicator;
import strategy.battle.operator.BattleOperatorCreator;
import strategy.battle.operator.BattleOperatorCreatorImpl;
import strategy.gui.GUI;
import strategy.initializer.app.AppInitializer;
import strategy.initializer.app.AppInitializerFromFile;
import strategy.initializer.gui.GUIInitializer;
import strategy.initializer.gui.GUIInitializerImpl;
import strategy.initializer.simulation.SimulationInitializer;

public class Simulation {

    //TODO: IMPORTANT THING! RECEIVE MESSAGES FROM GENERAL -> its easy for current implementation, but what if castle
    // has different armies etc

    //TODO: error handling layer, when critical app error catched display error and shut down app , when app error display it only
    //  Also handle exception: SafeDisable(); -> then display something and end app

    //TODO: write unit tests

    // TODO: think about automatic vs manual simulation

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
        GUI gui = guiInitializer.initializeGUI();
        AppCommunicator appCommunicator = gui.getAppCommunicator();
        //createErrorLayer()
        //appCommunicator.bindErrorsSender(errorLayer);
        //errorLayer->execute(simulationMethod());
        BattleOperatorCreator battleOperatorCreator = new BattleOperatorCreatorImpl();
        AppInitializer appInitializer = new AppInitializerFromFile(battleOperatorCreator);
        SimulationInitializer simulationInitializer = appInitializer.createSimulationInitializer();
        simulationInitializer.initializeSimulation(appCommunicator);
    }
}
