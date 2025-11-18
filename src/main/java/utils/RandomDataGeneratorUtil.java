package utils;

import enums.RandomDataGeneratorEnum;
import net.datafaker.Faker;

public class RandomDataGeneratorUtil {
    private static final Faker faker = new Faker();

    static final String FIRST_NAME = faker.name().firstName();

    static String month = String.format("%02d", faker.number().numberBetween(1, 12));
    static String year = String.format("%02d", faker.number().numberBetween(24, 35)); // 2024–2035

    static String expiry = month + "/" + year;

    public static String getRandomData(RandomDataGeneratorEnum genData){
        return switch (genData){
            case USER_EMAIL -> faker.internet().emailAddress();
            case USER_PASSWORD -> faker.internet().password();
            case FIRST_NAME -> FIRST_NAME;
            case LAST_NAME -> faker.name().lastName();
            case ADDRESS -> faker.address().fullAddress();
            case ADDRESS_2 -> faker.address().streetAddress();
            case ZIP_CODE -> faker.address().zipCode();
            case NAME_ON_CARD ->  FIRST_NAME + faker.name().lastName();
            case CREDIT_CARD_NUMBER -> faker.finance().creditCard().replaceAll("[^0-9]", "");
            case EXPIRATION -> expiry;
        };
    }

}
