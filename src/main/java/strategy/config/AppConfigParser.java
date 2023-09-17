package strategy.config;

import org.json.JSONException;
import strategy.app.AppConfig;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.simulation.SimulationType;
import strategy.simulation.type.SimulationTypeParser;
import strategy.simulation.type.SimulationTypeParserImpl;

public class AppConfigParser implements ConfigParser<AppConfig> {

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
        SimulationTypeParser parser = new SimulationTypeParserImpl();
        return parser.parse(strSimulationType);
    }
}
