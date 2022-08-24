package guru.springframework.sfgdi.config;

import com.springframework.pets.CatPetService;
import com.springframework.pets.DogPetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * Created by igors on 7/28/22
 */
//File set in @PropertySource was deleted after demonstration, it just contained 3 properties with values which were moved to application.properties
//@PropertySource("classpath:datasource.properties")  //88. Using Properties Source

@ImportResource("classpath:sfgdi-config.xml")  //alternatively bean context can be specified in the Spring-application class - SfgDiApplication
@Configuration
public class GreetingServiceConfig {

    //88. Using Properties Source
    //${guru.} values taken from application.properties (or application-dev.properties or application-qa.properties - depending on the profile used
    @Bean
    FakeDataSource fakeDataSource(@Value("${guru.username}") String username,
                                  @Value("${guru.password}") String password,
                                  @Value("${guru.jdbcurl}") String jbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jbcurl);
        return fakeDataSource;
    }

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
    /*See @ImportResource("classpath:sfgdi-config.xml") at the top of this class, which does the same as the commented
    code below - initializes a bean instance according to the XML config file
     */
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
