package guru.springframework.sfgdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Created by igors on 8/24/22
 */
// binding class variables (properties) to profile properties via constructor of bean class
/* Because "@Configuration" is used, this class doesn't have to be initialized in GreetingServiceConfig
The "guru" value inside @ConfigurationProperties helps Spring to understand which properties should be taken
from the application profile ("guru" - is the prefix for variables which this class will have binding to)
So if you have many properties with different prefixes, it will use only those, which have "guru" prefix
Spring does reflection and sees names of properties in this class, taking into consideration the prefix "guru".
So, "username" property in the class is binding to "guru.username" property in the profile. And so on with others...
 */
@ConstructorBinding
@ConfigurationProperties("guru") //This requires to add @EnableConfigurationProperties(SfgConstructorConfig.class) in GreetingsServiceConfig class
public class SfgConstructorConfig {
    private final String username;
    private final String password;
    private final String jdbcurl;

    //Spring will initialize binding via this constructor (because @ConstructorBinding annotation used)
    public SfgConstructorConfig(String username, String password, String jdbcurl) {
        this.username = username;
        this.password = password;
        this.jdbcurl = jdbcurl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }
}
