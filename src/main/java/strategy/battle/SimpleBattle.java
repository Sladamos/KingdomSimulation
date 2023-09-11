package strategy.battle;

import strategy.item.military.ArmyDestroyedException;
import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.notifier.MessagesNotifier;
import strategy.message.receiver.MessagesReceiver;

public class SimpleBattle implements Battle {

    private final Kingdom firstKingdom;

    private final Kingdom secondKingdom;

    private final MessagesNotifier<StringMessage> messagesNotifier;

    private boolean areKingdomsFighting;

    public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom, MessagesNotifier<StringMessage> messagesNotifier) {
        this.firstKingdom = firstKingdom;
        this.secondKingdom = secondKingdom;
        this.messagesNotifier = messagesNotifier;
        areKingdomsFighting = false;
    }

    @Override
    public void run() {
        areKingdomsFighting = true;
        while(areKingdomsFighting) {
            simulateAttack(firstKingdom, secondKingdom);
            if(areKingdomsFighting) {
                simulateAttack(secondKingdom, firstKingdom);
            }
        }
        messagesNotifier.removeListeners();
    }

    private void simulateAttack(Kingdom attacker, Kingdom defender) {
        try {
            long attackTime = attacker.getAttackTime().getMiliseconds();
            Thread.sleep(attackTime);
            StringMessage messageAboutAttack = new StringMessage(attacker + " attacked");
            messagesNotifier.accept(messageAboutAttack);
            attacker.attack(defender);
        } catch (ArmyDestroyedException ignored) {
            StringMessage messageAboutWon = new StringMessage(attacker + " won the battle");
            messagesNotifier.accept(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }

    @Override
    public void addListener(MessagesReceiver<StringMessage> messagesReceiver) {
        messagesNotifier.addListener(messagesReceiver);
    }
}
