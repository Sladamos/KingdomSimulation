package strategy.battle;

import strategy.military.army.ArmyDestroyedException;
import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class SimpleAttackSimulator implements AttackSimulator {

    private final Kingdom attacker;

    private final Kingdom defender;

    private final MessagesNotifier<StringMessage> messagesNotifier;

    private boolean areKingdomsFighting;

    public SimpleAttackSimulator(Kingdom attacker, Kingdom defender, MessagesNotifier<StringMessage> messagesNotifier) {
        this.attacker = attacker;
        this.defender = defender;
        this.messagesNotifier = messagesNotifier;
        areKingdomsFighting = false;
    }

    @Override
    public void simulateAttacking() {
        try {
            areKingdomsFighting = true;
            while (areKingdomsFighting) {
                simulateAttack();
            }
        } finally {
            messagesNotifier.removeListeners();
        }
    }

    private void simulateAttack() {
        try {
            long attackTime = attacker.getAttackTime().getMiliseconds();
            Thread.sleep(attackTime);
            executeAttackIfPossible();
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }

    private void executeAttackIfPossible() {
        synchronized (attacker) {
            if(attacker.isDead()) {
                throw new ArmyDestroyedException();
            }
            StringMessage messageAboutAttack = new StringMessage(attacker + " attacked");
            messagesNotifier.accept(messageAboutAttack);
            executeAttack();
        }
    }

    private void executeAttack() {
        try {
            attacker.attack(defender);
        }
        catch (ArmyDestroyedException ignored) {
            attackerWonTheBattle();
        }
    }

    private void attackerWonTheBattle() {
        StringMessage messageAboutWon = new StringMessage(attacker + " won the battle");
        endBattle(messageAboutWon);
    }

    private void endBattle(StringMessage endMessage) {
        messagesNotifier.accept(endMessage);
        areKingdomsFighting = false;
    }

    @Override
    public void addListener(MessagesReceiver<StringMessage> messagesReceiver) {
        messagesNotifier.addListener(messagesReceiver);
    }
}
