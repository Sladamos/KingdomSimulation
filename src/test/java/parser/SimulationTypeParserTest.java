package parser;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.CriticalAppError;
import strategy.kingdom.KingdomType;

public class SimulationTypeParserTest {

	private static SimulationTypeParser parser;

	@BeforeAll
	public void createParser() {
		parser = new SimulationTypeParserImpl();
	}

	@Test
	public void returnSarraxType_when_stringSarraxPassed() {
		assertThat(parser.parse("sarrax")).isEqualTo(KingdomType.SARRAX);
	}

	@Test
	public void throwCriticalAppException_when_incorrectStringPassed() {
		assertThatThrownBy(parser.parse("incorrect_type")).isInstanceOf(CriticalAppError.class);
	}
}
