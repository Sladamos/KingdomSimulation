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
        String filePath = createFilePath("randomFileWhichDoesNotExist.json");
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile(filePath);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void throwCriticalException_when_errorDuringLoadingJsonFile() {
        String filePath = createFilePath("randomFileWithIncorrectBrackets.json");
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile(filePath);
        assertThatThrownBy(executedMethod).isInstanceOf(CriticalAppError.class);
    }

    @Test
    public void doesNotThrowAnyException_when_fileLoadedProperly() {
        String filePath = createFilePath("properJsonFile.json");
        ThrowingCallable executedMethod = () -> loader.loadJsonFromFile(filePath);
        assertThatCode(executedMethod).doesNotThrowAnyException();
    }

    private String createFilePath(String fileName) {
        return "./test/" + fileName;
    }
}
