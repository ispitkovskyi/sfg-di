package guru.springframework.sfgdi.config;

import com.springframework.pets.CatPetService;
import com.springframework.pets.DogPetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.*;

/**
 * Created by igors on 7/28/22
 */
@ImportResource("classpath:sfgdi-config.xml")  //alternatively it also can be specified in the Spring-application class - SfgDiApplication
@Configuration
public class GreetingServiceConfig {

    @Bean
    PetServiceFactory getPetServiceFactory(){
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    DogPetService dogPetService(PetServiceFactory petServiceFactory){
        return (DogPetService) petServiceFactory.getPetService("dog");
    }

    @Profile("cat")
    @Bean
    CatPetService catPetService(PetServiceFactory petServiceFactory){
        return (CatPetService) petServiceFactory.getPetService("cat");
    }

    @Profile({"ES", "default"})
    @Bean("i18nService") // - explicitly define name of bean created by this method, as it is expected in a Qualifier in controller-class I18nController
    I18nSpanishService i18nSpanishService(){
        return new I18nSpanishService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    /*here name of method (-> mane of bean it creates) is different from the class name
      because originally this service was annotated as @Service("i18nService") in I18nEnglishGreetingService class
      so we want to keep that name, as it's used as a Qualifier in controller-class I18nController
     */
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    //Instead of Java config it is defined in XML config file:  src/main/resources/sfgdi-config.xml
    /*
    @Bean
    ConstructorGreetingService constructorGreetingService(){  //name of this method will be name of the Bean it returns!!!
        return new ConstructorGreetingService();
    }
    */

    //Having this, you may remove @Service annotation from the ConstructorGreetingService class to exclude it from Spring scan
    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
