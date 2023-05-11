package guru.springframework.sfgdi.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/19/22
 */
//@Profile({"ES", "default"})  //commented, because definition of profile was moved from Spring-scanning mechanism to Java-based configuration class - GreetingServiceConfig
//@Service("i18nService")
public class I18nSpanishService implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Hola Mundo - ES";
    }
}
