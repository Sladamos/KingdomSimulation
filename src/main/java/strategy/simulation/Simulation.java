package strategy.simulation;

import strategy.initializer.AutomaticSimulationInitializer;

public class Simulation {

    //TODO: validate config file in config initializers (are values > 0 etc.)

    //TODO: refactor messages from string to json! and create new messages: StorageMessage / BattleMessage
    //TODO: before simulation initialize there should be created all gui (ApplicationMessenger)
    //  then there should exist: BattleReceiver FirstKingdomMessenger SecondKingdomMessenger : for example in
    //  battlereceiver create StringProperty, and change it when new messsage will come.
    //TODO: messages receiver System.out.println("Consumed :" + material); in STORAGES!!! additionaly some ID  (KingdomFirst: Consumed: material)
    ////TODO: messages receiver System.out.println("Produced :" + item);


    //TODO: add sleep time to config???? and boolean to specify automatic / manual -> refactor after messages

    /*TODO gui:
        two panels with names of materials and count of each one (consider it!):
        three buttons -> Start developing / add infantry  -> new Window (number / maxDamage / maxDefense)/ terminate
        one button -> start battle
        mid panel -> battle results
        button to clear panel
        layer for error handling -> both for console and gui
     */

    //TODO: error handling layer, when critical app error catched display error and shut down app , when app error display it only
    //  Also handle exception: SafeDisable(); -> then display something and end app

    public static void main(String[] args) {
        AutomaticSimulationInitializer simulationInitializer = new AutomaticSimulationInitializer();
        simulationInitializer.initializeSimulation();
    }
}
