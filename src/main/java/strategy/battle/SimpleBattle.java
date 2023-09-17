package strategy.battle;

import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleBattle implements Battle {

	private final Kingdom firstKingdom;

	private final Kingdom secondKingdom;

	private final MessagesNotifier<StringMessage> messagesNotifier;

	private final ExecutorService executor;

	public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom, MessagesNotifier<StringMessage> messagesNotifier) {
		this.firstKingdom = firstKingdom;
		this.secondKingdom = secondKingdom;
		this.messagesNotifier = messagesNotifier;
		executor = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		AttackSimulator firstAttackSimulator = new SimpleAttackSimulator(firstKingdom, secondKingdom, messagesNotifier);
		AttackSimulator secondAttackSimulator = new SimpleAttackSimulator(secondKingdom, firstKingdom, messagesNotifier);
		executor.execute(firstAttackSimulator::simulateAttacking);
		executor.execute(secondAttackSimulator::simulateAttacking);
		waitForBattleEnd();
		messagesNotifier.removeListeners();
	}

	private void waitForBattleEnd() {
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (Exception ignored) {
		}
	}

	@Override
	public void addListener(MessagesReceiver<StringMessage> messagesReceiver) {
		messagesNotifier.addListener(messagesReceiver);
	}

	@Override
	public void removeListeners() {
		messagesNotifier.removeListeners();
	}

	@Override
	public void accept(StringMessage message) {
		messagesNotifier.accept(message);
	}
}
