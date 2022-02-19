package guru.springframework.sfgdi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by igors on 2/19/22
 */
@Profile("EN")
@Service("i18nService")
public class I18nEnglishService implements GreetingService{

    @Override
    public String sayGreeting() {
        return "Hello World - EN";
    }
}
