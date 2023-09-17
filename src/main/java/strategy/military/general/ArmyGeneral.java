package strategy.military.general;

import strategy.message.JSONMessage;
import strategy.message.notifier.MessagesNotifier;

public interface ArmyGeneral extends General, MessagesNotifier<JSONMessage> {
}
