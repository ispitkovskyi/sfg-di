package guru.springframework.sfgdi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/19/22
 */

//@Primary tells Spring to use this class to create an instance of GreetingService super-type, when @Qualifier is NOT SPECIFIED
//For example see constructor in the MyController class
@Primary
@Service
public class PrimaryGreetingService implements GreetingService{
    @Override
    public String sayGreeting() {
        return "Hello World - From the PRIMARY Bean";
    }
}
