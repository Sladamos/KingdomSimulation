package strategy.simulation.type;

import strategy.error.BasicAppError;
import strategy.simulation.SimulationType;

import java.util.HashMap;
import java.util.Map;

public class SimulationTypeParserImpl implements SimulationTypeParser {

	private final Map<String, SimulationType> simulationTypes;

	public SimulationTypeParserImpl() {
		simulationTypes = new HashMap<>();
		simulationTypes.put("automatic", SimulationType.AUTOMATIC);
	}

	@Override
	public SimulationType parse(String parseStr) {
		if(!simulationTypes.containsKey(parseStr)) {
			throw new BasicAppError("Incorrect simulation type!");
		}
		return simulationTypes.get(parseStr);
	}
}
