package parser;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.BasicAppError;
import strategy.simulation.SimulationType;
import strategy.simulation.type.SimulationTypeParser;
import strategy.simulation.type.SimulationTypeParserImpl;

public class SimulationTypeParserTest {

	private static SimulationTypeParser parser;

	@BeforeAll
	public static void createParser() {
		parser = new SimulationTypeParserImpl();
	}

	@Test
	public void returnAutomaticType_when_stringAutomaticPassed() {
		assertThat(parser.parse("automatic")).isEqualTo(SimulationType.AUTOMATIC);
	}

	@Test
	public void throwBasicAppException_when_incorrectStringPassed() {
		assertThatThrownBy(() -> parser.parse("incorrect_type")).isInstanceOf(BasicAppError.class);
	}
}
