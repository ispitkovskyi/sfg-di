package guru.springframework.sfgdi.config;

import guru.springframework.sfgdi.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by igors on 7/28/22
 */
@Configuration
public class GreetingServiceConfig {


    @Profile({"ES", "default"})
    @Bean("i18nService") // - explicitly define name of bean created by this method, as it is expected in a Qualifier in controller-class I18nController
    I18nSpanishService i18nSpanishService(){
        return new I18nSpanishService();
    }

    @Profile("EN")
    @Bean
    /*here name of method (-> mane of bean it creates) is different from the class name
      because originally this service was annotated as @Service("i18nService") in I18nEnglishGreetingService class
      so we want to keep that name, as it's used as a Qualifier in controller-class I18nController
     */
    I18nEnglishGreetingService i18nService(){
        return new I18nEnglishGreetingService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }
    //Having this, you may remove @Service annotation from the ConstructorGreetingService class to exclude it from Spring scan
    @Bean
    ConstructorGreetingService constructorGreetingService(){  //name of this method will be name of the Bean it returns!!!
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
