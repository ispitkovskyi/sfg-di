package guru.springframework.sfgdi.controllers;

import guru.springframework.sfgdi.services.GreetingService;

/**
 * Created by igors on 2/18/22
 */
//THE WORST :( WAY FOR DEPENDENCY INJECTION - because the propety is PUBLIC!!!
public class PropertyInjectedController {
    public GreetingService greetingService;

    public String getGreeting() {
        return greetingService.sayGreeting();
    }
}
