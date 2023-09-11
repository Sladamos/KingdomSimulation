package strategy.battle;

import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

public interface Battle extends Runnable, MessagesSender<StringMessage> {
}
