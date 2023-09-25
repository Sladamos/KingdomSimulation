package strategy.error;

import strategy.app.AppExitable;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

public interface ErrorsReceiver extends MessagesReceiver<JSONMessage>, AppExitable {
}
