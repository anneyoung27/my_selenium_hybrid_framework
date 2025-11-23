package factory;

import helper.ExcelHelper;
import helper.SystemHelper;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderFactory {
    @DataProvider(name = "data_provider_01")
    public Object[][] dataProvider01() {
        return new Object[][]{{"First-Value"}, {"Second-Value"}, {"Third-Value"}};
    }

    @DataProvider(name = "data_provider_02")
    public Object[][] dataProvider02() {
        return new Object[][]{{"Value1", "Value2", "Value3"}, {"Value4", "Value5", "Value6"}};
    }

    @DataProvider(name = "data_provider_03", parallel = true)
    public Object[][] dataProvider03() {
        return new Object[][]{
                {"Viettel", "123456", 10, "Jakarta"},
                {"VNPT", "56789", 5, "Bandung"}
        };
    }

    @DataProvider(name = "data_provider_login", parallel = true)
    public Object[][] dataProviderLogin() {
        return new Object[][]{
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"}
        };
    }

    @DataProvider(name = "data_provider_login_excel", parallel = true)
    public Object[][] dataProviderLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/java/testdata/ExcelData.xlsx", "LoginDataProvider");
        LogUtils.info("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_hashtable", parallel = true)
    public Object[][] dataProviderLoginFromExcelWithHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashtable(SystemHelper.getCurrentDir() + "src/test/java/testdata/ExcelData.xlsx", "LoginDataProvider", 1, 2);
        LogUtils.info("Login Data from Excel (Hashtable): " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_hashtable_customer", parallel = true)
    public Object[][] dataProviderLoginFromExcelWithHashtable_Customer() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashtable(SystemHelper.getCurrentDir() + "src/test/java/testdata/ExcelData.xlsx", "CustomerDataProvider", 1, 1);
        LogUtils.info("Login Data from Excel (Hashtable) - Customer: " + data);
        return data;
    }
}
