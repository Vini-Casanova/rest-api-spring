package br.com.casanova.apispring;

import br.com.casanova.apispring.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

//    private static  final String template = "Hello , %s !";
//    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum" , method = RequestMethod.GET)
    public Double sum(
            @RequestParam(value = "FirstNumber") String FirstNumber,
            @RequestParam(value = "SecondNumber") String SecondNumber
            )throws Exception{

            if(!isNumeric(FirstNumber) || !isNumeric(SecondNumber)){
                throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
            }

            return doubleParser(FirstNumber) + doubleParser(SecondNumber);
    }

    @RequestMapping(value = "/subtraction" , method = RequestMethod.GET)
    public Double subtraction(
            @RequestParam(value = "FirstNumber") String FirstNumber,
            @RequestParam(value = "SecondNumber") String SecondNumber
    )throws Exception{

        if(!isNumeric(FirstNumber) || !isNumeric(SecondNumber)){
            throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
        }

        return doubleParser(FirstNumber) - doubleParser(SecondNumber);
    }

    @RequestMapping(value = "/multiplication" , method = RequestMethod.GET)
    public Double multiplication(
            @RequestParam(value = "FirstNumber") String FirstNumber,
            @RequestParam(value = "SecondNumber") String SecondNumber
    )throws Exception{

        if(!isNumeric(FirstNumber) || !isNumeric(SecondNumber)){
            throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
        }

        return doubleParser(FirstNumber) * doubleParser(SecondNumber);
    }

    @RequestMapping(value = "/division" , method = RequestMethod.GET)
    public Double division(
            @RequestParam(value = "FirstNumber") String FirstNumber,
            @RequestParam(value = "SecondNumber") String SecondNumber
    )throws Exception{

        if(!isNumeric(FirstNumber) || !isNumeric(SecondNumber)){
            throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
        }

        return doubleParser(FirstNumber) / doubleParser(SecondNumber);
    }

    @RequestMapping(value = "/average" , method = RequestMethod.GET)
    public Double average(
            @RequestParam(value = "FirstNumber") String FirstNumber,
            @RequestParam(value = "SecondNumber") String SecondNumber
    )throws Exception{

        if(!isNumeric(FirstNumber) || !isNumeric(SecondNumber)){
            throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
        }

        return (doubleParser(FirstNumber) + doubleParser(SecondNumber)) / 2;
    }

    @RequestMapping(value = "/root" , method = RequestMethod.GET)
    public Double squareRoot(
            @RequestParam(value = "Number") String FirstNumber
    )throws Exception{

        if(!isNumeric(FirstNumber)){
            throw new UnsupportedMathOperationException("Please set a numeric value on the variables");
        }

        return Math.sqrt(doubleParser(FirstNumber));
    }

    private static Double doubleParser(String StringNumber){
        if (StringNumber ==null || StringNumber.length() == 0) return 0D;
        String reformedNumber = StringNumber.replaceAll(",",".");

        if (isNumeric(reformedNumber)) return Double.parseDouble(reformedNumber);
        return 0D;
    }

    private static boolean isNumeric(String number){
        if (number == null || number.length() == 0) return false;
        String reformedNumber = number.replaceAll(",",".");
        return reformedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }



}
