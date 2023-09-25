package strategy.option.message;

import lombok.AllArgsConstructor;
import strategy.message.JSONMessage;
import strategy.option.NamedOption;
import strategy.simulation.api.SimulationAPI;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OptionMessagesCreatorImpl implements OptionMessagesCreator {

    private final SimulationAPI api;

    @Override
    public JSONMessage getCreatedKingdomsId() {
        String firstMessage = "Select Kingdom ID from below:\n";
        String kingdomsIds = api.getCreatedKingdomsId().stream().collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + kingdomsIds);
    }

    @Override
    public JSONMessage getCreatedBattlesId() {
        String firstMessage = "Select Battle ID from below:\n";
        String battlesIds = api.getCreatedBattlesId().stream().map(String::valueOf).collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + battlesIds);
    }

    @Override
    public JSONMessage createMessageAboutOptions(Map<String, NamedOption> managedOptions) {
        String firstMessage = "Select Option from below:\n";
        String options = managedOptions.values().stream().map(NamedOption::getName).collect(Collectors.joining("\n","",""));
        return new JSONMessage(firstMessage + options);
    }
}
