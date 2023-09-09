package strategy.error;

public abstract class AppError extends RuntimeException {

    public AppError() {
        super();
    }

    public AppError(String message) {
        super(message);
    }
}
