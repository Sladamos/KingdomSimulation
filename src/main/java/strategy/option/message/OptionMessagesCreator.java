package strategy.option.message;

import strategy.message.JSONMessage;

public interface OptionMessagesCreator {
    JSONMessage getCreatedKingdomsId();
    JSONMessage getCreatedBattlesId();
}
