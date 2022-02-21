package guru.springframework.sfgdi.controllers;

import guru.springframework.sfgdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by igors on 2/18/22
 */
//THE BEST :) WAY FOR DEPENDENCY INJECTION
@Controller
public class ConstructorInjectedController {
    private final GreetingService greetingService;

    //@Autowired - NOT NECESSARY FOR CONSTRUCTORS
    public ConstructorInjectedController(@Qualifier("constructorGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
