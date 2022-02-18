package guru.springframework.sfgdi.controllers;

import org.springframework.stereotype.Controller;

/**
 * Created by igors on 2/18/22
 */
@Controller
public class MyController {

    public String sayHello(){
        System.out.println("Hello World!!!");
        return "Hi Folks!";
    }
}
