package strategy.simulation.type;

import strategy.simulation.SimulationType;

public interface SimulationTypeParser {
	SimulationType parse(String parseStr);
}
