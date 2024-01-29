package automation.test;

import java.util.Map;

import org.testng.annotations.DataProvider;
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
	ExcelReader excelReader = new ExcelReader(
			"C:\\Users\\SuhasiniManuneethi\\OneDrive - Kadel Labs Private Limited\\Desktop\\Kl_Demo_FrameWorkSetup\\src\\test\\resources\\testData\\CRM_LoginData.xlsx");
	String sheetName = "Sheet1";
	String uniqueId = "UID_001";
	// private Logger Logger = LoggerFactory.getLogger(TestCase1.class);
	@DataProvider(name = "testData1")
	public Object[][] getData1() {

		Map<String, String> testData = excelReader.getDataById(sheetName, uniqueId);
		return convertDataListToArray(testData);
	}

	private Object[][] convertDataListToArray(Map<String, String> testData) {
		Object[][] dataArray = new Object[1][1];

		dataArray[0][0] = testData;

		return dataArray;
	}

	@Test(dataProvider = "testData1")
	 
	public void enteringCredentials(Map<String, String> testData) {
		
		String username = testData.get("Username");
		String password = testData.get("Password");

		if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			logger.info("SingIn into LogonPage");
			homePage.singInButton();
			logger.info("Verifying if Login text is present or not");
			loginPage.verfiyIfLoginPageLoaded();
			logger.info("Verifying if Enter Credentials");
			loginPage.enterCredentials(username, password);
		} else {

			logger.warning("Skipping test data with empty or null Username or Password.");
		}
	}



	 
//	@Test(dataProvider = "testData2")
//	public void notEnteringCredentials(Map<String, String> testData) {
//		
//		String username = testData.get("Username");
//		String password = testData.get("Password");
//		logger.info("SingIn into LogonPage");
//		homePage.singInButton();
//		logger.info("Verfing if Login text is present or not");
//		loginPage.verfiyIfLoginPageNotLoaded();
//		logger.info("Verfing if Enter Credentials");
//		loginPage.enterCredentials(username, password);
//		
//	}

}
