package strategy.storage;

import strategy.AppError;

public class CriticalAppError extends AppError {

    public CriticalAppError() {
    }

    public CriticalAppError(String message) {
        super(message);
    }
}
