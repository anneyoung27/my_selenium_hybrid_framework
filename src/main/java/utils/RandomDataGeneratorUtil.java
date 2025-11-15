package utils;

import enums.RandomDataGeneratorEnum;
import net.datafaker.Faker;

public class RandomDataGeneratorUtil {
    private static final Faker faker = new Faker();

    public static String getRandomData(RandomDataGeneratorEnum genData){
        return switch (genData){
            case USER_EMAIL -> faker.internet().emailAddress();
            case USER_PASSWORD -> faker.internet().password();
        };
    }
}
