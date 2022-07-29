package com.springframework.pets;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 12/28/19.
 */
//@Service // - moved this to GreetingsServiceConfig class (Java configuration of Spring)
//@Profile("cat")  // - moved this to GreetingsServiceConfig class (Java configuration of Spring)
public class CatPetService implements PetService {
    @Override
    public String getPetType() {
        return "Cats Are the Best!";
    }
}
