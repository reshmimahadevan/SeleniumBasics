package secondpackage;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Proj28_NewWindow {

	public static void main(String[] args) throws IOException {

		ChromeOptions option = new ChromeOptions();

		option.addArguments("--remote-allow-origins=*");

		WebDriver driver = WebDriverManager.chromedriver().capabilities(option).avoidShutdownHook().create();

		driver.get("https://rahulshettyacademy.com/angularpractice/");

		// Switching Window

		driver.switchTo().newWindow(WindowType.WINDOW);

		Set<String> handles = driver.getWindowHandles();

		Iterator<String> it = handles.iterator();

		String parentWindowId = it.next();

		String childWindow = it.next();

		driver.switchTo().window(childWindow);

		driver.get("https://rahulshettyacademy.com/");

		String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']"))

				.get(1).getText();

		driver.switchTo().window(parentWindowId);

		WebElement name = driver.findElement(By.cssSelector("[name='name']"));

		name.sendKeys(courseName);

		// Screenshot

		File file = name.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(file, new File("logo.png")); //Right click on the project name and refresh it to view the file --1st time

		// driver.quit();

		// GEt Height & Width

		System.out.println(name.getRect().getDimension().getHeight());

		System.out.println(name.getRect().getDimension().getWidth());

	}

}
