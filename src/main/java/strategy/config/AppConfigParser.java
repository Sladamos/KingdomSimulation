package strategy.config;

import org.json.JSONException;
import strategy.app.AppConfig;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.simulation.SimulationType;

import java.util.HashMap;
import java.util.Map;

public class AppConfigParser implements ConfigParser<AppConfig> {

    private final Map<String, SimulationType> simulationTypes;

    public AppConfigParser() {
        simulationTypes = new HashMap<>();
        simulationTypes.put("automatic", SimulationType.AUTOMATIC);
    }

    @Override
    public AppConfig createConfig(JSON json) {
        try {
            String simulationType = json.getString("simulation_type");
            return new AppConfig(getSimulationType(simulationType));
        }
        catch (JSONException | BasicAppError err) {
            throw new CriticalAppError("Something went wrong on creating app config. " + err.getMessage());
        }
    }

    private SimulationType getSimulationType(String strSimulationType) {
        if(!simulationTypes.containsKey(strSimulationType)) {
            throw new BasicAppError("Incorrect simulation type!");
        }
        return simulationTypes.get(strSimulationType);
    }
}
