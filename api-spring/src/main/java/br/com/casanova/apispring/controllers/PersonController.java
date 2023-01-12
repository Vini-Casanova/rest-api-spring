package br.com.casanova.apispring.controllers;

import br.com.casanova.apispring.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static br.com.casanova.apispring.parsers.DoubleConverter.doubleParser;
import static br.com.casanova.apispring.parsers.DoubleConverter.isNumeric;

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

}
