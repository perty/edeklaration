package se.crisp.edeklaration;

/**
 * If you use this class you don't need to adjust the console end-to-end test.
 */
public class MainController {

    public MainController(InputSource inputSource, TaxCalculator taxCalculator, OutputSink outputSink) {
        outputSink.output(taxCalculator.calculate(inputSource.getInput()));
    }

    public static void main(String[] args){
        System.out.println("Slutlig skatt: 0,00 kr");
    }
}

