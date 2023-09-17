package strategy.battle;

import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.sender.MessagesSender;

public interface Battle extends Runnable, MessagesNotifier<StringMessage> {
}
