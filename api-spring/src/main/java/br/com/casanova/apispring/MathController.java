package br.com.casanova.apispring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingsController {

    private static  final String template = "Hello , %s !";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greetings greeting(
            @RequestParam(value = "name", defaultValue = "Word")
            String name) {
        return new Greetings(counter.incrementAndGet(), String.format(template, name));
    }



}
