package strategy.battle;

import strategy.item.military.ArmyDestroyedException;
import strategy.kingdom.Kingdom;
import strategy.message.Message;
import strategy.message.receiver.MessagesReceiver;

public class SimpleBattle implements Battle {

    private final Kingdom firstKingdom;

    private final Kingdom secondKingdom;

    private final MessagesReceiver messagesReceiver;

    private boolean areKingdomsFighting;

    public SimpleBattle(Kingdom firstKingdom, Kingdom secondKingdom, MessagesReceiver messagesReceiver) {
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
            Message messageAboutAttack = new Message(attacker + " attacked");
            messagesReceiver.receiveMessage(messageAboutAttack);
            attacker.attack(defender);
        } catch (ArmyDestroyedException ignored) {
            Message messageAboutWon = new Message(attacker + " won the battle");
            messagesReceiver.receiveMessage(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }
}
