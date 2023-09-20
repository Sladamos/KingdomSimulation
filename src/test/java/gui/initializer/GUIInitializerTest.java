package gui.initializer;

import org.assertj.core.api.ThrowableAssert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import strategy.error.CriticalAppError;
import strategy.error.ErrorHandler;
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

    private JSON json;

    @BeforeEach
    public void createJsonLoaderMock() {
        jsonLoader = Mockito.mock(FileJsonLoader.class);
        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        guiInitializer = new GUIInitializerImpl(errorHandler);
        json = Mockito.mock(JSON.class);
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
