package strategy;

import strategy.initializer.SimulationInitializer;

public class Simulation {

    //TODO gui:
    /*
    two panels with names of materials and count of each one:
    three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
    one button -> start battle
    mid panel -> battle results
    button to clear panel
    layer for error handling -> both for console and gui
     */
    public static void main(String[] args) {
        SimulationInitializer simulationInitializer = new SimulationInitializer();
        simulationInitializer.initializeSimulation();
    }
}
