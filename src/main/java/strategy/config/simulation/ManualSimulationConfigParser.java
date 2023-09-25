package strategy.config.simulation;

import org.json.JSONException;
import strategy.config.KingdomConfigParser;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.ManualSimulationConfig;
import strategy.simulation.ManualSimulationConfigImpl;

public class ManualSimulationConfigParser implements SimulationConfigParser<ManualSimulationConfig> {
    @Override
    public ManualSimulationConfig createConfig(JSON json) {
        try {
            KingdomConfigParser kingdomConfigParser = new KingdomConfigParser();
            JSON firstKingdomJson = json.getJSONObject("first_kingdom");
            KingdomConfig firstKingdomConfig = kingdomConfigParser.createConfig(firstKingdomJson);
            JSON secondKingdomJson = json.getJSONObject("second_kingdom");
            KingdomConfig secondKingdomConfig = kingdomConfigParser.createConfig(secondKingdomJson);
            return new ManualSimulationConfigImpl(firstKingdomConfig, secondKingdomConfig);
        }
        catch (JSONException | BasicAppError err) {
            throw new CriticalAppError("Something went wrong on creating simulation config. " + err.getMessage());
        }
    }
}
