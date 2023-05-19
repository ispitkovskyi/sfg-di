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

/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Created by igors on 7/28/22
 * !!! This is a JAVA - CONFIGURATION class (Spring configuration, done using Java capabilities rather than Spring scan or !!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */

/**
 * File set in @PropertySource was deleted after demonstration, it just contained 3 properties with values which
 * were moved to application.properties.BAK
 * For more information seet 88. Using Properties Source
 *
 * Content of this properties file (properties values) will be available for use inside of Spring context
 */
//@PropertySource("classpath:datasource.properties")

/** the ImportResource annotation specifies config XML file, which contains list of beans to be created in additions to @Beans defined inside this class
 * so, now, we have a COMBINATION of configurations - 1st defined in this class (beans) and 2nd XML configuration specified in the ImportResource
*/
@ImportResource("classpath:sfgdi-config.xml")  //alternatively bean context (this annotatin) can be put into the Spring-application class - SfgDiApplication
@Configuration //Means, that this is a class, which contains configuration for the Spring project
@EnableConfigurationProperties(SfgConstructorConfig.class) //Here declare a class (or many classes), that we want to perform a constructor-binding for
public class GreetingServiceConfig {

    /**
     * ////////    PROPERTIES BINDING  ////////
     * 88. Using Properties Source
     *
     * EXPLANATION (READ IT !!!):
     * PROPERTIES BINDING - IS WHEN YOU WANT TO INIT SOME OF VARIABLES IN YOUR BEAN (CLASS) WITH VALUES FROM THE APPLICATION PROFILE
     * (.properties or .yml)
     *
     *${guru.} values taken from application.properties.BAK (or application-dev.properties.BAK
     * or application-qa.properties.BAK - depending on the profile used
     */
    /*@Bean
    FakeDataSource fakeDataSource(@Value("${guru.username}") String username,
                                  @Value("${guru.password}") String password,
                                  @Value("${guru.jdbcurl}") String jbcurl){
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jbcurl);
        return fakeDataSource;
    }*/

    /**
     * Spring will do dependency injection, injecting instance of SfgConfiguration here
     * This is alternative of using property binding via @Value annotation (see commented block above)
     */
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

    /**
     * We MUST create a englishGreetingRepository Spring-bean to add it to the Spring context.
     * Without it, method "i18nService" will be INVALID, because it expects for "englishGreetingRepository" bean
     * to be available in the spring context
     * Try commenting this code to see the problem not having this creation of the bean in-place
     */
    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile({"ES", "default"})
    @Bean("i18nService") // - explicitly define name of bean created by this method, as it is expected in a Qualifier in controller-class I18nController
    /* here string attribute inside of the @Bean annotation defines name of bean-instance this method creates
    * So, because you MUST NOT have same method name twice, in this case you use @Bean annotation to set the bean name
    * */
    I18nSpanishService i18nSpanishService(){
        return new I18nSpanishService();
    }

    @Profile("EN")
    @Bean
    /**
     * here name of method (-> mane of bean it creates) is different from the class name
      because originally this service was annotated as @Service("i18nService") in I18nEnglishGreetingService class
      so we want to keep that name, as it's used as a Qualifier in controller-class I18nController
     *
     * also, it expects for the "englishGreetingRepository" bean to be availalbe in the Spring context. And
     * this bean is created by the englishGreetingRepository() method above. The Spring context will automatically
     * wire-up this bean into the I18nEnglishGreetingService instance
     */
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary //This will be default instance, returned by Spring, when instance of GreetingService super-type is requested from ApplicationContext ctx
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    /**
    //Instead of creating this bean in this Java-config class, it is defined in XML config file:  src/main/resources/sfgdi-config.xml
    See @ImportResource("classpath:sfgdi-config.xml") at the top of this class, which does the same as the commented
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
