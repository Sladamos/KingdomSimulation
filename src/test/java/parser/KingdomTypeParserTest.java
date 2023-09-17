package parser;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.CriticalAppError;
import strategy.kingdom.type.KingdomType;
import strategy.kingdom.type.KingdomTypeParser;
import strategy.kingdom.type.KingdomTypeParserImpl;

public class KingdomTypeParserTest {

	private static KingdomTypeParser parser;

	@BeforeAll
	public static void createParser() {
		parser = new KingdomTypeParserImpl();
	}

	@Test
	public void returnSarraxType_when_stringSarraxPassed() {
		assertThat(parser.parse("sarrax")).isEqualTo(KingdomType.SARRAX);
	}

	@Test
	public void throwCriticalAppException_when_incorrectStringPassed() {
		assertThatThrownBy(() -> parser.parse("incorrect_type")).isInstanceOf(CriticalAppError.class);
	}
}
