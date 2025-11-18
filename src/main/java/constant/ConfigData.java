package constant;

import enums.RandomDataGeneratorEnum;
import helper.PropertiesHelper;
import utils.RandomDataGeneratorUtil;

public class ConfigData {
    public static String URL = PropertiesHelper.getValue("URL");
    public static String SEND_TO_EMAIL = PropertiesHelper.getValue("EMAIL");
    public static String SEND_TO_EMAIL_PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static int EXPLICIT_WAIT_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
    public static double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
    public static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
    public static String SCREENSHOT_PATH = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");

    public static String USER_EMAIL = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.USER_EMAIL);
    public static String USER_PASSWORD = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.USER_PASSWORD);
    public static String FIRST_NAME = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.FIRST_NAME);
    public static String LAST_NAME = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.LAST_NAME);
    public static String ADDRESS = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.ADDRESS);
    public static String ADDRESS_2 = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.ADDRESS_2);
    public static String ZIP = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.ZIP_CODE);
    public static String NAME_ON_CARD = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.NAME_ON_CARD);
    public static String CC_NUM = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.CREDIT_CARD_NUMBER);
    public static String EXPIRATION = RandomDataGeneratorUtil.getRandomData(RandomDataGeneratorEnum.EXPIRATION);

    
}
