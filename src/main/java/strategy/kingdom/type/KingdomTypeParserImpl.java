package strategy.kingdom.type;

import strategy.error.BasicAppError;

import java.util.HashMap;
import java.util.Map;

public class KingdomTypeParserImpl implements KingdomTypeParser {

	private final Map<String, KingdomType> kingdomTypes;

	public KingdomTypeParserImpl() {
		kingdomTypes = new HashMap<>();
		kingdomTypes.put("Sarrax", KingdomType.SARRAX);
	}

	@Override
	public KingdomType parse(String parse) {
		if(!kingdomTypes.containsKey(parse)) {
			throw new BasicAppError("Incorrect kingdom type.");
		}
		return kingdomTypes.get(parse);
	}
}
