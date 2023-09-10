package strategy.message.receiver;

import strategy.message.Message;

import java.util.function.Consumer;

public interface MessagesReceiver<T extends Message<?>> extends Consumer<T> {
}
