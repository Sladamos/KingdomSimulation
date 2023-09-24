package option.communicator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.gui.GUIType;
import strategy.option.communicator.ConsoleOptionsCommunicator;
import strategy.option.communicator.OptionsCommunicatorCreator;
import strategy.option.communicator.OptionsCommunicatorCreatorImpl;
import strategy.option.message.OptionMessagesCreator;

import static org.assertj.core.api.Assertions.*;

class OptionsCommunicatorCreatorTest {

    private static OptionMessagesCreator messagesCreator;

    @BeforeAll
    public static void initializeMessagesCreator() {
        messagesCreator = Mockito.mock(OptionMessagesCreator.class);
    }

    @Test
    void returnConsoleOptionsCommunicator_when_consoleGuiTypePassed() {
        OptionsCommunicatorCreator creator = new OptionsCommunicatorCreatorImpl(messagesCreator);
        assertThat(creator.createOptionsCommunicator(GUIType.CONSOLE)).isInstanceOf(ConsoleOptionsCommunicator.class);
    }
}