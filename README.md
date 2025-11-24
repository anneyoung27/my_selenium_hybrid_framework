🍂 🍃 Selenium Hybrid Framework 🍃 🍂

## What's in this repository?
This project is a web automation using Selenium WebDriver. It automates web interactions for testing purposes, ensuring the functionality of web applications.

## What's the purpose of this project?
The purpose of this project is to implement what I have learned in web automation testing using Selenium with Java programming language.

## Key Features
- TestNG for test execution
- Parallel execution testing
- Maven for dependency management
- Configurable test execution using true and false
- Logging
- Data Driven Testing (DDT) with Excel and @DataProvider
- ExtentReports and Allure for test report
- Capture error screenshot for fail test case
- Capture screenshot while testing step by step
- Record video when running a test case 
- Read config and locators from properties file
- Data Generator using Data Faker

## Technologies
- Java 21
- Apache Maven
- Selenium
- TestNG Framework
- Log4j2
- ExtentReport
- Allure Report
- Data Faker

## Project Structure
```
reports/                         # Test execution reports (HTML)
logs/                            # Application and automation logs
src/
├── main/
│   ├── java/
│   │   └── constants/           # Constant values used across the framework
│   │       ├── enums/           # Enum definitions for reusable test values
│   │       ├── factory/         # WebDriver / object factory classes
│   │       ├── helper/          # Helper utilities (Excel, Capture, Properties, System)
│   │       ├── keywords/        # Reusable automation actions (keywords)
│   │       ├── listeners/       # TestNG listeners for reporting & logging
│   │       ├── objects/         # Web locators
│   │       ├── pages/           # Page Object Model (POM) classes
│   │       ├── reports/         # Report manager & ExtentReport configuration
│   │       └── utils/           # Utility classes and common framework functions (Log and Random Data Generator)
│   └── resources/
│       ├── config.properties    # Framework configuration (URL, browser, etc.)
│       └── log4j2.properties    # Logging configuration
│
└── pom.xml                      # Maven project configuration
```

### 1. Project URL
```https://sweetshop.netlify.app/```

### 2. UI Test
The UI tests cover the functionality of the website, focusing on key features such as:
- AddAndCheckoutProduct
- AddPopularProduct
- NavigateAboutPage
- UserLogin
- E2E Test
- Data Driven Test:
    - LoginTestWithDataProvider
    - LoginTestWithExcel

### 3. Viewing the reports
a. Extent Reports<br />
The test reports can be found in:<br />
```./reports```<br />
The report will be generated upon execution and will include screenshots if any failures occur during the testing process.

b. Allure Report<br />
To run Allure report inside the target directory<br />
`allure serve target/allure-results`<br />
or, to generate the report without opening it automatically:<br />
`allure generate target/allure-results -o target/allure-report --clean`<br />
Then manually open the report:<br />
`allure open target/allure-report`

### 3. Record and Capture Every Step During Testing
a. Recording video:<br />
Use the following commands to start and stop recording around your test logic:
```
CaptureHelper.startRecord(method_name);
// ... test logic here ...
CaptureHelper.stopRecord();
```
b. Capturing screenshots:<br />
Use the screenshot method before or after your test steps:
```
CaptureHelper.screenshot(step);
// ... test logic here ...
CaptureHelper.screenshot(step);
```
c. Viewing the results:<br />
All recording outputs and screenshots are stored in:<br />
`./exports/videos`<br />
`./exports/screenshots`


## Installation
1. Clone this repository:<br />
   `git clone https://github.com/anneyoung27/my_selenium_hybrid_framework.git`

2. Navigate to the project directory:<br />
   `cd my_selenium_hybrid_framework`

3. Install dependencies using Maven:<br />
   `mvn clean install`

4. Run<br />
   `src/test/resources/testcases` or `src/test/resources/dataDrivenTest`

