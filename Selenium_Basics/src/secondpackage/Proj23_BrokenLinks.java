package secondpackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Proj23_BrokenLinks {

	public static void main(String[] args) throws IOException {

		ChromeOptions option = new ChromeOptions();

		option.addArguments("--remote-allow-origins=*");

		WebDriver driver = WebDriverManager.chromedriver().capabilities(option).avoidShutdownHook().create();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// broken URL

		// Step 1 - Is to get all urls tied up to the links using Selenium

		// Java methods will call URL's and gets you the status code

		// if status code >400 then that url is not working-> link which tied to url is
		// broken

		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));

		SoftAssert a = new SoftAssert();

		for (WebElement link : links)

		{

			String url = link.getAttribute("href");

			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

			conn.setRequestMethod("HEAD");

			conn.connect();

			int respCode = conn.getResponseCode();

			System.out.println(respCode);

			a.assertTrue(respCode < 400, "The link with Text" + link.getText() + " is broken with code" + respCode);

		}

		a.assertAll();

	}

}
