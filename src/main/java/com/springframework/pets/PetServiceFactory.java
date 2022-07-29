package com.springframework.pets;

/**
 * Created by igors on 7/29/22
 */
public class PetServiceFactory {

    public PetService getPetService(String petType){
        switch(petType){
            case "dog":
                return new DogPetService();
            case "cat":
                return new CatPetService();
            default:
                return new DogPetService();
        }
    }
}
