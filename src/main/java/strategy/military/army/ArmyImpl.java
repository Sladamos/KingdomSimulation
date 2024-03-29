package strategy.military.army;

import strategy.action.Attack;
import strategy.action.BasicAttack;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.military.MilitaryUnit;
import strategy.military.mechanism.fight.Fightable;
import strategy.message.JSONMessage;
import strategy.message.receiver.MessagesReceiver;

import java.util.Collection;
import java.util.LinkedList;

public class ArmyImpl implements Army {

	private final Collection<MilitaryUnit> army;

	private final int damageModifier;

	private final OneArgEvent<JSONMessage> messageEvent;

	public ArmyImpl(int damageModifier) {
		army = new LinkedList<>();
		this.damageModifier = damageModifier;
		messageEvent = new OneArgEventImpl<>();
	}

	@Override
	public synchronized void onMoraleChanged(int morale) {
		int damageModifier = morale / this.damageModifier;
		army.parallelStream().forEach(el -> el.setDamageModifier(damageModifier));
	}

	@Override
	public synchronized void accept(MilitaryUnit militaryUnit) {
		army.add(militaryUnit);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> messagesReceiver) {
		messageEvent.addListener(messagesReceiver);
	}

	@Override
	public synchronized Attack createAttack() {
		Collection<Attack> attacks = army.parallelStream().map(Fightable::createAttack).toList();
		return new BasicAttack(this, attacks);
	}

	@Override
	public synchronized void attack(Fightable fightable) {
		Attack attack = createAttack();
		fightable.getHit(attack);
	}

	@Override
	public synchronized void getHit(Attack attack) {
		Collection<Attack> attacks = attack.getCombination();
		for (Attack att: attacks) {
			if(!isDead()) {
				hitUnitWithAttack(att);
			}
		}
		checkArmyStatus();
	}

	@Override
	public synchronized boolean isDead() {
		return army.isEmpty();
	}

	private void hitUnitWithAttack(Attack attack) {
		MilitaryUnit unit = army.iterator().next();
		try {
			unit.getHit(attack);
		} finally {
			if (unit.isDead()) {
				army.remove(unit);
			}
		}
	}

	private void checkArmyStatus() {
		if (isDead()) {
			generateMessageAboutArmyDestroyed();
			throw new ArmyDestroyedException();
		} else {
			generateMessageAboutArmyStatus();
		}
	}

	private void generateMessageAboutArmyStatus() {
		JSONMessage message = new JSONMessage("Units left in army: " + army.size());
		message.put("size", army.size());
		messageEvent.invoke(message);
	}

	private void generateMessageAboutArmyDestroyed() {
		JSONMessage message = new JSONMessage("Army destroyed");
		message.put("size", army.size());
		messageEvent.invoke(message);
	}
}
