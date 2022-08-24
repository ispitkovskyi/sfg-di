package guru.springframework.sfgdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by igors on 8/24/22
 */
/* Because "@Configuration" is used, this class doesn't have to be initialized in GreetingServiceConfig
The "guru" value inside @ConfigurationProperties helps Spring to understand which properties should be taken
from the application profile ("guru" - is the prefix for variables which this class will have binding to)
So if you have many properties with different prefixes, it will use only those, which have "guru" prefix
Spring does reflection and sees names of properties in this class, taking into consideration the prefix "guru".
So, "username" property in the class is binding to "guru.username" property in the profile. And so on with others...
 */
@ConfigurationProperties("guru")
@Configuration
public class SfgConfiguration {
    private String username;
    private String password;
    private String jdbcurl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }
}
