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
            firstKingdomAttacks();
            if(areKingdomsFighting) {
                secondKingdomAttacks();
            }
        }
    }

    private void firstKingdomAttacks() {
        try {
            Thread.sleep(10000);
            String messageAboutAttack = "First kingdom attacked";
            messagesReceiver.receiveMessage(messageAboutAttack);
            firstKingdom.attack(secondKingdom);
        } catch (ArmyDestroyedException ignored) {
            String messageAboutWon = "First kingdom won the battle";
            messagesReceiver.receiveMessage(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }

    }

    private void secondKingdomAttacks() {
        try {
            Thread.sleep(10000);
            String messageAboutAttack = "Second kingdom attacked";
            messagesReceiver.receiveMessage(messageAboutAttack);
            secondKingdom.attack(firstKingdom);
        } catch (ArmyDestroyedException ignored) {
            String messageAboutWon = "Second kingdom won the battle";
            messagesReceiver.receiveMessage(messageAboutWon);
            areKingdomsFighting = false;
        } catch (Exception ignored) {
            areKingdomsFighting = false;
        }
    }

}
