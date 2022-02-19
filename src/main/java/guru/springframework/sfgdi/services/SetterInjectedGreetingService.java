package guru.springframework.sfgdi.services;

import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/19/22
 */
@Service
public class SetterInjectedGreetingService implements GreetingService{
    @Override
    public String sayGreeting() {
        return "Hello World - Setter";
    }
}
