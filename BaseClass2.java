package week5.day2;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class BaseClass2 {
	ChromeDriver driver;
	String fileName;

	@Parameters({ "url", "uName", "pword" })
	@BeforeMethod()
	public void beforeMethod(String url, String uName, String pword) throws InterruptedException {
		System.out.println("@BeforeMethod Method");

		WebDriverManager.chromedriver().setup();

		// Open browser
		driver = new ChromeDriver();

		// Load url
		driver.get(url);
		// maxi
		driver.manage().window().maximize();
		// Step2: Enter username (Check for frame before entering the username)
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(uName);
		// Step3: Enter password
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(pword);
		// Step4: Click Login
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incidents");
		
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//div[@class = 'sn-widget-list-title'][text() = 'Incidents']")).click();
		Thread.sleep(2000);
		// Step6: Click “All”
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.xpath("//span[@id='incident_breadcrumb']/a[1]")).click();
		
	}

	@AfterMethod
	public void postCondition() {
		// Close browser
		driver.close();
	}
	@DataProvider(name = "sendData")
	public String[][] sendData() throws IOException {
		String[][] data = ExcelFamily.readExcel(fileName);
		return data;
	}
}
