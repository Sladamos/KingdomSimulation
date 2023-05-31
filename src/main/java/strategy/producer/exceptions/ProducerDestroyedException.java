package strategy.producer.exceptions;

public class ProducerDestroyedException extends RuntimeException {

    public ProducerDestroyedException(String message) {
        super(message);
    }

    public ProducerDestroyedException() {
    }
}
