package automation.test;

import java.util.Map;

import org.testng.annotations.Test;

import baseTest.runner;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;
import utils.ExcelReader;

public class TestCase1 extends runner {
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();
	
    //private Logger Logger = LoggerFactory.getLogger(TestCase1.class);
    
	@Test(dataProvider = "testData1", dataProviderClass = ExcelReader.class)
	public void enteringCredentials(Map<String, String> testData) {
		String username = testData.get("Username");
		String password = testData.get("Password");
		logger.info("SingIn into LogonPage");
		homePage.singInButton();
		logger.info("Verfing if Login text is present or not");
		loginPage.verfiyIfLoginPageLoaded();
		logger.info("Verfing if Enter Credentials");
		loginPage.enterCredentials(username, password);
		
		//logger.error("Error Level Log");
	}

	@Test(dataProvider = "testData2", dataProviderClass = ExcelReader.class)
	public void notEnteringCredentials(Map<String, String> testData) {
		String username = testData.get("Username");
		String password = testData.get("Password");
		//Logger.info("SingIn into LogonPage");
		homePage.singInButton();
		logger.info("Verfing if Login text is present or not");
		loginPage.verfiyIfLoginPageNotLoaded();
		logger.info("Verfing if Enter Credentials");
		loginPage.enterCredentials(username, password);
		//Logger.error("Error Level Log");
	}

}
