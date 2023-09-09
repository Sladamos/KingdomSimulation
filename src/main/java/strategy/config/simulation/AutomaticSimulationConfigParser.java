package strategy.config.simulation;

import org.json.JSONException;
import strategy.CriticalAppError;
import strategy.config.KingdomConfigParser;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.simulation.AutomaticSimulationConfig;
import strategy.simulation.AutomaticSimulationConfigImpl;
import util.Time;
import util.TimeImpl;

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
			if(firstKingdomDevelopmentTime.getSeconds() < 0 || secondKingdomDevelopmentTime.getSeconds() < 0) {
				throw new JSONException("Kingdom development time can't be less than zero");
			}
			return new AutomaticSimulationConfigImpl(firstKingdomConfig, secondKingdomConfig, firstKingdomDevelopmentTime, secondKingdomDevelopmentTime);
		}
		catch (JSONException err) {
			throw new CriticalAppError("Something went wrong on creating simulation config. " + err.getMessage());
		}
	}


}
