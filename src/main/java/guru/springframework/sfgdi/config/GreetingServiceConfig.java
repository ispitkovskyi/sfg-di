package guru.springframework.sfgdi.config;

import com.springframework.pets.CatPetService;
import com.springframework.pets.DogPetService;
import com.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * Created by igors on 7/28/22
 * This is a JAVA - CONFIGURATION class (Spring configuration, done using Java capabilities rather than Spring scan or
 */

//File set in @PropertySource was deleted after demonstration, it just contained 3 properties with values which were moved to application.properties.BAK
//@PropertySource("classpath:datasource.properties")  //88. Using Properties Source
@ImportResource("classpath:sfgdi-config.xml")  //alternatively bean context can be specified in the Spring-application class - SfgDiApplication
@Configuration //Means, that this is a class, which contains configuration for the Spring project
@EnableConfigurationProperties(SfgConstructorConfig.class) //Here declare a class (or many classes), that we want to perform a constructor-binding for
public class GreetingServiceConfig {

    ////////////////////////////////    PROPERTIES BINDING  //////////////////////////////////////////////
    //88. Using Properties Source

    // EXPLANATION (READ IT !!!):
    // PROPERTIES BINDING - IS WHEN YOU WANT TO INIT SOME OF VARIABLES IN YOUR BEAN (CLASS) WITH VALUES FROM THE APPLICATION PROFILE
    // (.properties or .yml)

    //${guru.} values taken from application.properties.BAK (or application-dev.properties.BAK or application-qa.properties.BAK - depending on the profile used
/*    @Bean
    FakeDataSource fakeDataSource(@Value("${guru.username}") String username,
                                  @Value("${guru.password}") String password,
                                  @Value("${guru.jdbcurl}") String jbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jbcurl);
        return fakeDataSource;
    }*/

    //Spring will do dependency injection, injecting instance of SfgConfiguration here
    //This is alternative of using property binding via @Value annotation (see commented block above)
/*    @Bean
    FakeDataSource fakeDataSource(SfgConfiguration sfgConfiguration){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(sfgConfiguration.getUsername());
        fakeDataSource.setPassword(sfgConfiguration.getPassword());
        fakeDataSource.setJdbcurl(sfgConfiguration.getJdbcurl());
        return fakeDataSource;
    }*/

    /**
     * the "@Bean" annotation is used on methods, which return instances of different component-classes, to make these
     * instances a Spring components. So, they were automatically included into the Spring context
     */

    //Spring will do dependency injection, injecting instance of SfgConstructorConfig here
    //This is alternative of using property binding via @Value annotation (see commented block above)
    //This way we create IMMUTABLE object of configuration class (no setters for properties)
    @Bean
    FakeDataSource fakeDataSource(SfgConstructorConfig sfgConstructorConfig){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(sfgConstructorConfig.getUsername());
        fakeDataSource.setPassword(sfgConstructorConfig.getPassword());
        fakeDataSource.setJdbcurl(sfgConstructorConfig.getJdbcurl());
        return fakeDataSource;
    }
    ////////////////////////////////    PROPERTIES BINDING  //////////////////////////////////////////////

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

    /**
     * Having this, you may remove @Service annotation from the ConstructorGreetingService class to exclude it from Spring scan
     * @Bean annotation used to make the returned instance be a Spring component instance (included into the Spring context)
     */
    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){ //name of this method will be name of the Bean it returns!!!
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){  //name of this method will be name of the Bean it returns!!!
        return new SetterInjectedGreetingService();
    }
}
