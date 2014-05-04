package se.crisp.edeklaration.endtoend;

import org.junit.Test;
import se.crisp.edeklaration.endtoend.util.SimpleOsCommandExecutor;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

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
    public void ingenInkomstIngenSkatt() {
        SimpleOsCommandExecutor executor =
                new SimpleOsCommandExecutor(getJavaExecutablePath(),
                        Arrays.asList("java", "-cp",
                                getBuildDir().getAbsolutePath(),
                                "se.crisp.edeklaration.TaxCalculator", "0", "Stockholms"));
        executor.run();
        assertEquals("Slutlig skatt: 0,00 kr", executor.getOutput().get(0));
    }

    private String getJavaExecutablePath() {
        return new File(System.getenv("JAVA_HOME"), "bin").getAbsolutePath();
    }

    private File getTestedClass() {
        return new File(getBuildDir(), "se/crisp/edeklaration/TaxCalculator.class");
    }

    private File getBuildDir() {
        return new File(System.getProperty("user.dir"), "build/classes/main");
    }
}
