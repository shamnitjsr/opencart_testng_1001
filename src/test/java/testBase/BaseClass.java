package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {

	public WebDriver driver;
	
	public Logger logger;	//Log4j

	@BeforeClass
	@Parameters({"os","browser"})
	
	public void setup(String os, String br) {

		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()){
		
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "firefox":
			driver=new FirefoxDriver();
			break;
		case "edge":
			driver=new ChromeDriver();
			break;
		default:
			System.out.println("Invalid Browser name..");
			return;
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	public static String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public static String randomeNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public static String randomealphanumeric() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return (generateString + "@" + generatedNumber);
	}

}
