package strategy.gui;

public class GUIInputHandlerManager {
//
//    private final AppController inputHandler;
//
//    private final OptionsExecutioner optionsExecutioner;
//
//    private boolean isLaunched;
//
//    public GUIInputHandlerManager(AppController inputHandler, OptionsExecutioner optionsExecutioner) {
//        this.inputHandler = inputHandler;
//        this.optionsExecutioner = optionsExecutioner;
//        isLaunched = false;
//    }
//
//    @Override
//    public synchronized void enableInputHandling() {
//        //optionsExecutioner.getOptionsBuffer().enableAcceptingItems();
//        inputHandler.enableInputHandling();
//        optionsExecutioner.enableExecuting();
//        isLaunched = true;
//    }
//
//    @Override
//    public void addInputHandledListener(Consumer<String> listener) {
//        inputHandler.addInputHandledListener(listener);
//    }
//
//    @Override
//    public synchronized void disableInputHandling() {
//        optionsExecutioner.disableExecuting();
//        //optionsExecutioner.getOptionsBuffer().disableAcceptingItems();
//        inputHandler.disableInputHandling();
//        isLaunched = false;
//        notifyAll();
//    }
//
//    @Override
//    public synchronized void waitOnAppClose() {
//        while(isLaunched) {
//            try {
//                wait();
//            } catch (InterruptedException ignored) {
//            }
//        }
//    }

}
