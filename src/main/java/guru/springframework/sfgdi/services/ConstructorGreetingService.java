package guru.springframework.sfgdi.services;

import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/18/22
 */
//@Service
public class ConstructorGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World - Constructor";
    }
}
