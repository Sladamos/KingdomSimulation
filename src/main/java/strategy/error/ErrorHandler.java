package strategy.error;

public interface ErrorHandler {
    void runInErrorHandler(Runnable runnable);
}
