package gui.parser;

import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.error.CriticalAppError;
import strategy.initializer.gui.GUIInitializer;
import strategy.initializer.gui.GUIInitializerImpl;
import strategy.json.FileJsonLoader;
import strategy.json.JSON;
import strategy.json.JsonLoader;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GUIInitializerTest {

    private GUIInitializer guiInitializer;

    private JsonLoader jsonLoader;

    @BeforeEach
    public void createJsonLoaderMock() {
        jsonLoader = Mockito.mock(FileJsonLoader.class);
        guiInitializer = new GUIInitializerImpl();
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotExist() {
        Mockito.when(jsonLoader.loadJson()).thenThrow(CriticalAppError.class);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotContainAnyType() {
        JSON json = Mockito.mock(JSON.class);
        Mockito.when(json.getString("type")).thenThrow(CriticalAppError.class);
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_configFileDoesNotContainProperType() {
        JSON json = Mockito.mock(JSON.class);
        Mockito.when(json.getString("type")).thenReturn("incorrect_test_type");
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void doesNotThrowAnyException_when_configFileContainsConsoleType() {
        JSON json = Mockito.mock(JSON.class);
        Mockito.when(json.getString("type")).thenReturn("console");
        Mockito.when(jsonLoader.loadJson()).thenReturn(json);
        ThrowingCallable executedMethod = () -> guiInitializer.initializeGUI(jsonLoader);
        assertThatCode(executedMethod).doesNotThrowAnyException();
    }
}
