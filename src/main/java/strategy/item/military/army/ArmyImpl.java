package strategy.item.military.army;

import strategy.action.Attack;
import strategy.events.oneargevent.OneArgEvent;
import strategy.events.oneargevent.OneArgEventImpl;
import strategy.item.military.MilitaryUnit;
import strategy.mechanism.fight.Fightable;
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
	public void onMoraleChanged(int morale) {
		int damageModifier = morale / this.damageModifier;
		army.parallelStream().forEach(el -> el.setDamageModifier(damageModifier));
	}

	@Override
	public void accept(MilitaryUnit militaryUnit) {
		army.add(militaryUnit);
	}

	@Override
	public void addListener(MessagesReceiver<JSONMessage> messagesReceiver) {
		messageEvent.addListener(messagesReceiver);
	}

	@Override
	public void attack(Fightable fightable) {
		army.parallelStream().forEach(e -> e.attack(fightable));
	}

	@Override
	public synchronized void getHit(Attack attack) {
		hitUnitWithAttack(attack);
		checkArmyStatus();
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
		if (army.isEmpty()) {
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
