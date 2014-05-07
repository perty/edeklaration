package se.crisp.edeklaration.endtoend;

import org.junit.Test;
import se.crisp.edeklaration.endtoend.util.SimpleOsCommandExecutor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Simple "end-to-end" test that invokes a java class with two arguments.
 * Add tests to this and refactor where needed as you go along.
 */
public class TaxCalculatorEndToEndTest {

    @Test
    public void IsJavaHomePresent() {
        assertNotNull(getJavaExecutablePath());
    }

    @Test
    public void IsTestedClassPresent() {
        assertTrue(getTestedClass().exists());
    }

    @Test
    public void no_income_then_no_tax() {
        SimpleOsCommandExecutor executor =
                new SimpleOsCommandExecutor(getJavaExecutablePath(), getCommand());
        executor.run();
        assertEquals("Slutlig skatt: 0,00 kr", executor.getOutput().get(0));
    }

    private List<String> getCommand() {
        return Arrays.asList("java", "-cp",
                getBuildDir().getAbsolutePath(),
                "se.crisp.edeklaration.MainController", "0", "Stockholms");
    }

    private String getJavaExecutablePath() {
        return checkFile(new File(System.getenv("JAVA_HOME"), "bin")).getAbsolutePath();
    }

    private File getTestedClass() {
        return checkFile(new File(getBuildDir(), "se/crisp/edeklaration/MainController.class"));
    }

    private File getBuildDir() {
        return checkFile(new File(System.getProperty("user.dir"), "build/classes/main"));
    }

    private File checkFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException("Can't find: " + file);
        }
        return file;
    }
}
