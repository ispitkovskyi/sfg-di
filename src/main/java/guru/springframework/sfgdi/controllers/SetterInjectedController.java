package guru.springframework.sfgdi.controllers;

import guru.springframework.sfgdi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Created by igors on 2/18/22
 */

//ACCEPTABLE :| WAY FOR DEPENDENCY INJECTION
@Controller
public class SetterInjectedController {

    private GreetingService greetingService;

    //@Qualifier("setterInjectedGreetingService") //Can also be here instead of being in-line with method declaration
    @Autowired
    public void setGreetingService(@Qualifier("setterInjectedGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getGreeting(){
        return greetingService.sayGreeting();
    }
}
