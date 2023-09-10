package strategy.error;

public class ErrorHandlerImpl implements ErrorHandler {
    //Error messages can be string only
    @Override
    public void runInErrorHandler(Runnable runnable) {
        try {
            System.out.println("tedst");
        } catch (CriticalAppError err) {
            //ErrorsReceiver->receiveCriticalError();
            System.out.println("tedst");
            System.exit(404);
        } catch (BasicAppError err) {
            //ErrorsReceiver->receiveBasicError();
            System.out.println("tedst");
        } catch (Exception err) {
            //ErrorsReceiver->receiveCriticalError();
            //Something very wrong and exit
            System.out.println("tedst");
            System.exit(404);
        }

    }
}
