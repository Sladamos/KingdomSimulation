package parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.BasicAppError;
import strategy.simulation.type.SimulationType;
import strategy.simulation.type.SimulationTypeParser;
import strategy.simulation.type.SimulationTypeParserImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
	public void returnManualType_when_stringManualPassed() {
		assertThat(parser.parse("manual")).isEqualTo(SimulationType.MANUAL);
	}


	@Test
	public void throwBasicAppException_when_incorrectStringPassed() {
		assertThatThrownBy(() -> parser.parse("incorrect_type")).isInstanceOf(BasicAppError.class);
	}
}
