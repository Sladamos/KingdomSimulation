package strategy;

import strategy.initializer.SimulationInitializer;

public class Simulation {

    //TODO gui:
    /*
    two panels with names of materials and count of each one (consider it!):
    three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
    one button -> start battle
    mid panel -> battle results
    button to clear panel
    layer for error handling -> both for console and gui
     */

    //TODO: before simulation initialize there should be created all gui (ApplicationMessenger)
    //then there should exist: BattleReceiver FirstKingdomMessenger SecondKingdomMessenger : for example in
    //battlereceiver create StringProperty, and change it when new messsage will come.

    //TODO: error handling layer, when critical app error catched display error and shut down app , when app error display it only
    //PROBLEM: how to shut down child threads?
    public static void main(String[] args) {
        SimulationInitializer simulationInitializer = new SimulationInitializer();
        simulationInitializer.initializeSimulation();
    }
}
