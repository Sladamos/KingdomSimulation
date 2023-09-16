package json;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import strategy.error.CriticalAppError;
import strategy.json.FileJsonLoader;
import strategy.json.FileJsonLoaderImpl;

import static org.assertj.core.api.Assertions.*;

public class FileJsonLoaderTest {

    private static FileJsonLoader loader;

    @BeforeAll
    public static void initializeLoader()
    {
        loader = new FileJsonLoaderImpl();
    }

    @Test
    public void throwCriticalException_when_fileDoesNotExist() {
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile("randomFileWhichDoesNotExist");
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_errorDuringLoadingJsonFile() {
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile("randomFileWithIncorrectBrackets");
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void doesNotThrowAnyException_when_fileLoadedProperly() {
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile("properJsonFile");
        assertThatCode(executedMethod).doesNotThrowAnyException();
    }
}
