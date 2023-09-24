package gui.initializer;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import strategy.buffer.SwitchableBuffer;
import strategy.error.CriticalAppError;
import strategy.initializer.gui.GUIInitializer;
import strategy.initializer.gui.GUIInitializerImpl;
import strategy.json.FileJsonLoader;
import strategy.json.JSON;
import strategy.json.JsonLoader;
import strategy.option.communicator.OptionsCommunicator;
import strategy.option.communicator.OptionsCommunicatorCreator;
import strategy.provider.battle.BattleIdProvider;
import strategy.provider.kingdom.KingdomIdProvider;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GUIInitializerTest {

    private GUIInitializer guiInitializer;

    private JsonLoader jsonLoader;

    private JSON json;

    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private SwitchableBuffer<String> buffer;

    @BeforeEach
    public void createJsonLoaderMock() {
        jsonLoader = Mockito.mock(FileJsonLoader.class);
        json = Mockito.mock(JSON.class);
    }

    @BeforeEach
    public void createGuiInitializer() {
        OptionsCommunicatorCreator optionsCommunicatorCreator = Mockito.mock(OptionsCommunicatorCreator.class);
        OptionsCommunicator optionsCommunicator = Mockito.mock(OptionsCommunicator.class);
        Mockito.when(optionsCommunicatorCreator.createOptionsCommunicator(Mockito.any())).thenReturn(optionsCommunicator);
        Mockito.when(optionsCommunicator.getKingdomIdProvider()).thenReturn(Mockito.mock(KingdomIdProvider.class));
        Mockito.when(optionsCommunicator.getBattleIdProvider()).thenReturn(Mockito.mock(BattleIdProvider.class));
        Mockito.when(optionsCommunicator.getOptionsBuffer()).thenReturn(buffer);
        guiInitializer = new GUIInitializerImpl(optionsCommunicatorCreator);
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotExist() {
        Mockito.when(jsonLoader.loadJson()).thenThrow(CriticalAppError.class);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotContainAnyType() {
        Mockito.when(json.getString("type")).thenThrow(CriticalAppError.class);
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotContainProperType() {
        Mockito.when(json.getString("type")).thenReturn("incorrect_test_type");
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void doesNotThrowAnyException_when_configFileContainsConsoleType() {
        Mockito.when(json.getString("type")).thenReturn("console");
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatCode(executedMethod).doesNotThrowAnyException();
    }
}
