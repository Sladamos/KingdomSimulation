package strategy.config;

import strategy.AppError;
import strategy.SimulationConfig;
import strategy.json.JSON;
import strategy.kingdom.KingdomConfig;

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
		catch (Exception err) {
			throw new AppError("Something went wrong on creating simulation config. " + err.getMessage());
		}
	}
}
