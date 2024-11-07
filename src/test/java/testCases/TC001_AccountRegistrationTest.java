package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test
	public void verify_account_registration() {

		logger.info("************ Starting TC001_AccountRegistrationTest ************");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Link...");
			hp.clickRegister();
			logger.info("Clicked on Register Link...");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details...");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");
			regpage.setTelephone(randomeNumber());

			String password = randomealphanumeric();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("******** Validating Expected message ********");

			String confmsg = regpage.getConfirmationMsg();
			
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test Failed");
				logger.debug("Debug logs");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {

			Assert.fail();
		}
		logger.info("******** Finished TC001_AccountRegistrationTest ********");
	}

}
