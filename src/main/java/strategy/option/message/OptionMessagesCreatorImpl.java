package strategy.option.message;

import lombok.AllArgsConstructor;
import strategy.message.JSONMessage;
import strategy.option.Option;
import strategy.simulation.api.SimulationAPI;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OptionMessagesCreatorImpl implements OptionMessagesCreator {

    private final SimulationAPI api;

    @Override
    public JSONMessage getCreatedKingdomsId() {
        String firstMessage = "Select Kingdoms ID from below:\n";
        String kingdomsIds = api.getCreatedKingdomsId().stream().collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + kingdomsIds);
    }

    @Override
    public JSONMessage getCreatedBattlesId() {
        String firstMessage = "Select Battles ID from below:\n";
        String battlesIds = api.getCreatedBattlesId().stream().map(String::valueOf).collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + battlesIds);
    }

    @Override
    public JSONMessage createMessageAboutOptions(Map<String, Option> managedOptions) {
        String firstMessage = "Select Option from below:\n";
        String options = managedOptions.values().stream().map(Option::getName).collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + options);
    }
}
