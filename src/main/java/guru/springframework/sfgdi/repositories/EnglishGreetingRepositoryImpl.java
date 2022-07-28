package guru.springframework.sfgdi.repositories;

/**
 * Created by igors on 7/28/22
 */
public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {

    @Override
    public String getGreetings() {
        return "Hello World - EN";
    }
}
