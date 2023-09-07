package strategy.config;

import org.json.JSONException;
import strategy.SimulationConfig;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;
import strategy.CriticalAppError;

public class SimulationConfigParser implements ConfigParser<SimulationConfig>{
	@Override
	public SimulationConfig createConfig(JSON json) {
		try {
			KingdomConfigParser kingdomConfigParser = new KingdomConfigParser();
			JSON firstKingdomJson = json.getJSONObject("first_kingdom");
			KingdomConfig firstKingdomConfig = kingdomConfigParser.createConfig(firstKingdomJson);
			JSON secondKingdomJson = json.getJSONObject("second_kingdom");
			KingdomConfig secondKingdomConfig = kingdomConfigParser.createConfig(secondKingdomJson);
			return new SimulationConfig(firstKingdomConfig, secondKingdomConfig);
		}
		catch (JSONException err) {
			throw new CriticalAppError("Something went wrong on creating simulation config. " + err.getMessage());
		}
	}


}
