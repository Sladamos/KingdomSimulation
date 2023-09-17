package parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.BasicAppError;
import strategy.gui.GUIType;
import strategy.gui.type.GUITypeParser;
import strategy.gui.type.GUITypeParserImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GUITypeParserTest {

	private static GUITypeParser parser;

	@BeforeAll
	public static void createParser() {
		parser = new GUITypeParserImpl();
	}

	@Test
	public void returnConsoleType_when_stringConsolePassed() {
		assertThat(parser.parse("console")).isEqualTo(GUIType.CONSOLE);
	}

	@Test
	public void throwBasicAppException_when_incorrectStringPassed() {
		assertThatThrownBy(() -> parser.parse("incorrect_type")).isInstanceOf(BasicAppError.class);
	}
}
