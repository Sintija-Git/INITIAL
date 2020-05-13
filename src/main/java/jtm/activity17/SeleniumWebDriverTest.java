package jtm.activity17;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import jtm.testSuite.JTMTest;
/*-
 * This test is open sourced intentionally. You can use it as a template for
 * automated Web GUI test in your teamwork project.
 * This is Selenium WebDriver test and you can generate code for it with Selenium IDE.
 * For more info look at:
 *   — https://www.seleniumhq.org/docs/02_selenium_ide.jsp
 *   — https://www.seleniumhq.org/docs/03_webdriver.jsp
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumWebDriverTest extends JTMTest {
	private static WebDriver driver;
	private static String baseUrl;
	private static final int timeout = 10; // maximum timeout in seconds

	@BeforeClass
	public static void setUp() {
		// Run web server if not started already
		try {
			URL status = new URL("http://localhost:8080/");
			status.getContent();
		} catch (Exception e) {
			try {
				JettyApplication.main(new String[] { "" });
				Thread.sleep(10); // wait 10 ms
				logger.info("Web application started");
			} catch (Exception e1) {
				Assert.fail(e1.toString());
			}
		}
		// If you didn't update the Path system variable to add the full
		// directory path to the executable as above mentioned then doing this
		// directly through code
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
		// Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		// WebDriver driver = new MarionetteDriver(capabilities);
		driver = new FirefoxDriver();
		// URL to the tested web application
		baseUrl = "http://localhost:8080/";
		// Default timeout
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void test01Insert() {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Insert teacher")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Name");
		driver.findElement(By.name("surname")).clear();
		driver.findElement(By.name("surname")).sendKeys("Surname");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		sleep();
		driver.findElement(By.linkText("Back")).click();
	}

	@Test
	public void test02Search() {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Find teacher")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Name");
		driver.findElement(By.name("surname")).clear();
		driver.findElement(By.name("surname")).sendKeys("Surname");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		sleep();
		driver.findElement(By.linkText("Back")).click();
	}

	@Test
	public void test03Delete() {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("Delete teacher")).click();
		driver.findElement(By.name("id")).clear();
		driver.findElement(By.name("id")).sendKeys("12");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		sleep();
		driver.findElement(By.linkText("Back")).click();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	private void sleep() {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
