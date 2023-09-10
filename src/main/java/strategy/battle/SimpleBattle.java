package strategy.battle;

import strategy.item.military.ArmyDestroyedException;
import strategy.kingdom.Kingdom;
import strategy.message.StringMessage;
import strategy.message.receiver.MessagesReceiver;

public class SimpleBattle implements Battle {

    private final Kingdom firstKingdom;

    private final Kingdom secondKingdom;

    private final MessagesReceiver<StringMessage> messagesReceiver;

    private boolean areKingdomsFighting;

    public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom, MessagesReceiver<StringMessage> messagesReceiver) {
        this.firstKingdom = firstKingdom;
        this.secondKingdom = secondKingdom;
        this.messagesReceiver = messagesReceiver;
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
    }

    private void simulateAttack(Kingdom attacker, Kingdom defender) {
        try {
            long attackTime = attacker.getAttackTime().getMiliseconds();
            Thread.sleep(attackTime);
            StringMessage messageAboutAttack = new StringMessage(attacker + " attacked");
            messagesReceiver.accept(messageAboutAttack);
            attacker.attack(defender);
        } catch (ArmyDestroyedException ignored) {
            StringMessage messageAboutWon = new StringMessage(attacker + " won the battle");
            messagesReceiver.accept(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }
}
