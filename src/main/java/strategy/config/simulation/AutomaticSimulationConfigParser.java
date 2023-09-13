package strategy.config.simulation;

import org.json.JSONException;
import strategy.error.BasicAppError;
import strategy.error.CriticalAppError;
import strategy.config.KingdomConfigParser;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.AutomaticSimulationConfig;
import strategy.simulation.AutomaticSimulationConfigImpl;
import strategy.util.Time;
import strategy.util.TimeImpl;

public class AutomaticSimulationConfigParser implements SimulationConfigParser<AutomaticSimulationConfig> {
	@Override
	public AutomaticSimulationConfig createConfig(JSON json) {
		try {
			KingdomConfigParser kingdomConfigParser = new KingdomConfigParser();
			JSON firstKingdomJson = json.getJSONObject("first_kingdom");
			KingdomConfig firstKingdomConfig = kingdomConfigParser.createConfig(firstKingdomJson);
			JSON secondKingdomJson = json.getJSONObject("second_kingdom");
			KingdomConfig secondKingdomConfig = kingdomConfigParser.createConfig(secondKingdomJson);
			JSON simulationSpecifiedJson = json.getJSONObject("automatic_simulation");
			Time firstKingdomDevelopmentTime = new TimeImpl(simulationSpecifiedJson.getInt("first_kingdom_development"));
			Time secondKingdomDevelopmentTime = new TimeImpl(simulationSpecifiedJson.getInt("second_kingdom_development"));
			if(firstKingdomDevelopmentTime.getSeconds() <= 0 || secondKingdomDevelopmentTime.getSeconds() <= 0) {
				throw new BasicAppError("Kingdom development time must be greater than 0");
			}
			return new AutomaticSimulationConfigImpl(firstKingdomConfig, secondKingdomConfig, firstKingdomDevelopmentTime, secondKingdomDevelopmentTime);
		}
		catch (JSONException | BasicAppError err) {
			throw new CriticalAppError("Something went wrong on creating simulation config. " + err.getMessage());
		}
	}


}
