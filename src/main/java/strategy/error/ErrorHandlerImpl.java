package strategy.error;

import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public class ErrorHandlerImpl implements ErrorHandler {

    private final OneArgEvent<JSONMessage> criticalErrorOccured;

    private final OneArgEvent<JSONMessage> basicErrorOccured;

    public ErrorHandlerImpl() {
        criticalErrorOccured = new OneArgEventImpl<>();
        basicErrorOccured = new OneArgEventImpl<>();
        Thread.setDefaultUncaughtExceptionHandler((thread, e) -> notifyAboutUnspecifiedException());
    }

    @Override
    public void runInErrorHandler(Runnable runnable) {
        try {
            runnable.run();
        } catch (CriticalAppError err) {
            criticalErrorOccured.invoke(new JSONMessage(err.getMessage()));
        } catch (BasicAppError err) {
            basicErrorOccured.invoke(new JSONMessage(err.getMessage()));
        } catch (Exception err) {
            notifyAboutUnspecifiedException();
        }
    }

    private void notifyAboutUnspecifiedException() {
        criticalErrorOccured.invoke(new JSONMessage("Something went very wrong"));
    }

    @Override
    public void addListener(MessagesReceiver<JSONMessage> messagesReceiver) {
        basicErrorOccured.addListener(messagesReceiver);
        criticalErrorOccured.addListener(messagesReceiver);
    }
}
