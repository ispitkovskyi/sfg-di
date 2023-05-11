package guru.springframework.sfgdi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/19/22
 */

//@Primary tells Spring to use this class to create an instance of GreetingService super-type, when @Qualifier is NOT SPECIFIED
//For example see constructor in the MyController class
//@Primary  //commented, because definition of primary bean-type was moved from Spring-scanning mechanism to Java-based configuration class - GreetingServiceConfig
//@Service  //commented, because creation of instance for this class was moved to the GreetingsServiceConfig class, as part of Java-based configuration of Springboot project see GreetingServiceConfig
public class PrimaryGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello World - From the PRIMARY Bean";
    }
}
