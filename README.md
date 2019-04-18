# WebElementsTests project

This project is test automation framework which realizes interaction with the most common type of web elements and uses the most popular techniques.

The created methods for working with web elements are enough to make various test scenarios. In the project I used the Singleton, the Page Object and the Factory patterns. All tests can run multithreaded.

A couple of tests realize DDT technique: TestNG data provider, reading test data from CSV and JSON files.

The test framework includes logging and reporting.

## Tools
Java, Maven, Selenium WebDriver, Selenium Grid, TestNG, Log4j, Allure

## Features
The report is a result of tests so I paid special attention to logging and reporting.

The project uses a thread-safe singleton logger. The logger makes personal log file for each test method run “on the fly” and attaches it to Allure report. It’s really handy to inspect.

There is also used Allure reporting features for showing the most important test steps. The framework makes a screenshot on each test failure and shows it within Allure report.

## Usage

### How to run the project on Windows OS
1. deploy Selenium grid server or skip this step and run locally
2. run the test suite with parameters

### How to deploy Selenium grid
*(as an example locally with two nodes)*
1. download [Selenium Standalone Server](https://www.seleniumhq.org/download/)
2. create a new hub
  * open a new command line
  * create the hub
  ```java -jar [$user.dir]\selenium-server-standalone-<version>.jar -role hub
  ```
3. open in browser server by URL for checking **http://localhost:4444/wd/hub**
4. create the first node
  * open a new command line and go to the project folder ```cd [$user.dir]/webElementsTests
  ```
  * create the first default node
  * run the node with googlechrome browsers
```java -Dwebdriver.chrome.driver=.\drivers\chromedriver.exe -jar [$user.dir]\selenium-server-standalone-<version>.jar -role node -hub http://localhost:4444/grid/register -port 5555
```
  * or run the node with firefox browsers 
```java -Dwebdriver.gecko.driver=.\drivers\geckodriver.exe -jar [$user.dir]\selenium-server-standalone-<version>.jar -role node -hub http://localhost:4444/grid/register -port 5555
```
 5. create the second node
  * open a new command line and go to the project folder
 ```cd [$user.dir]/webElementsTests
 ```
  * create the second default node
  * run the node with googlechrome browsers
```java -Dwebdriver.chrome.driver=.\drivers\chromedriver.exe -jar [$user.dir]\selenium-server-standalone-<version>.jar -role node -hub http://localhost:4444/grid/register -port 5556
```
  * or run the node with firefox browsers
```java -Dwebdriver.gecko.driver=.\drivers\geckodriver.exe -jar [$user.dir]\selenium-server-standalone-<version>.jar -role node -hub http://localhost:4444/grid/register -port 5556
```
6. go to grid hub console for cheking **http://localhost:4444/grid/console**

### How to run the tests suite

1. open command line
2. go to the project folder

```cd [$user.dir]**/webElementsTests
```
3. run testNG suite by default *(on googlechrome locally)* 

```mvn clean test -Dsurefire.suiteXmlFiles=.\src\test\resources\xml\allTestsSuite.xml
```
or add parameters:
  * run on Selenium grid server `-Dselenium.server=grid -Dserver.url="http://localhost:4444/wd/hub"`
  * run on googlechrome `-Dselenium.browser=googlechrome`
  * run on firefox `-Dselenium.browser=firefox`
  * run on googlechrome headless `-Dselenium.browser=googlechrome.headless`
  * run on firefox headless `-Dselenium.browser=firefox.headless`
  * run on googlechrome mobile emulation and add mobile device name 
  ```-Dselenium.browser=mobile -Dselenium.deviceName="Pixel 2"
  ```
  *you can use any mobile device name from googlechrome list*
  
4. generate allure report `mvn allure:serve`

## For feedback

**e-mail:** mary.geraseva@gmail.com

**telegram:** @MaryGeraseva

**skype:** mary_geraseva

[linkedIn](https://www.linkedin.com/in/maria-geraseva/)
