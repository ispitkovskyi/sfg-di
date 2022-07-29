package com.springframework.pets;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 12/28/19.
 */
//@Profile({"dog", "default"})      // - moved this to GreetingsServiceConfig class (Java configuration of Spring)
//@Service      // - moved this to GreetingsServiceConfig class (Java configuration of Spring)
public class DogPetService implements PetService {
    public String getPetType(){
        return "Dogs are the best!";
    }
}
