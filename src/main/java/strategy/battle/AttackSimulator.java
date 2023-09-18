package strategy.battle;

import strategy.message.StringMessage;
import strategy.message.sender.MessagesSender;

public interface AttackSimulator extends MessagesSender<StringMessage> {
	void simulateAttacking();
}
