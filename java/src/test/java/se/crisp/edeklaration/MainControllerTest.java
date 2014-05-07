package se.crisp.edeklaration;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainControllerTest {

    private OutputSink outputSinkMock;
    private TaxCalculator taxCalculatorMock;
    private InputSource inputSourceMock;
    private CalculationInput calculationInput = new CalculationInput();

    @Before
    public void setUp() throws Exception {
        outputSinkMock = mock(OutputSink.class);
        taxCalculatorMock = mock(TaxCalculator.class);
        inputSourceMock = mock(InputSource.class);
        when(inputSourceMock.getInput()).thenReturn(calculationInput);
    }


    @Test
    public void should_calculation_result_push_to_output() {
        CalculationResult result = new CalculationResult(4711.5);
        when(taxCalculatorMock.calculate(calculationInput)).thenReturn(result);

        createAndExecuteController();

        verify(outputSinkMock).output(result);
    }

    @Test
    public void should_use_input_to_calculate() throws Exception {

        createAndExecuteController();

        verify(inputSourceMock).getInput();
        verify(taxCalculatorMock).calculate(calculationInput);
    }

    private void createAndExecuteController() {
        new MainController(inputSourceMock, taxCalculatorMock, outputSinkMock);
    }
}
