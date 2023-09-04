package strategy.battle;

import strategy.kingdom.Kingdom;
import strategy.message.receiver.MessagesReceiver;
import strategy.military.ArmyDestroyedException;

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
            long attackTime = attacker.getAttackTime();
            Thread.sleep(attackTime);
            String messageAboutAttack = attacker + " attacked";
            messagesReceiver.receiveMessage(messageAboutAttack);
            attacker.attack(defender);
        } catch (ArmyDestroyedException ignored) {
            String messageAboutWon = attacker + " won the battle";
            messagesReceiver.receiveMessage(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }
}
