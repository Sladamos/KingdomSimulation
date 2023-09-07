package strategy.storage;

import strategy.AppError;

public class StorageTerminatedException extends AppError {

    public StorageTerminatedException() {
        super();
    }

    public StorageTerminatedException(String message) {
        super(message);
    }
}
