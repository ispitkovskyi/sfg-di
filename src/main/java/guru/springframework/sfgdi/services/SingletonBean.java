package guru.springframework.sfgdi.services;

import org.springframework.stereotype.Component;

/**
 * Created by igors on 7/29/22
 */
@Component
public class SingletonBean {
    public SingletonBean() {
        System.out.println("Creating a Singleton bean!!!");
    }

    public String getMyScope(){
        return "I'm a Singleton";
    }
}
