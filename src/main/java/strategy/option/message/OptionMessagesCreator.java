package strategy.option.message;

import strategy.message.JSONMessage;
import strategy.option.NamedOption;

import java.util.Map;

public interface OptionMessagesCreator {
    JSONMessage getCreatedKingdomsId();
    JSONMessage getCreatedBattlesId();
    JSONMessage createMessageAboutOptions(Map<String, NamedOption> managedOptions);
}
