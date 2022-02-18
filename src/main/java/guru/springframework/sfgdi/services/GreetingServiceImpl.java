package guru.springframework.sfgdi.services;

/**
 * Created by igors on 2/18/22
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello World";
    }
}
