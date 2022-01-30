package week5.day2;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class BaseClass1 {
	ChromeDriver driver;
	String fileName;

	@Parameters({ "url", "userName", "password" })
	@BeforeMethod()
	public void beforeMethod(String url, String userName, String password) {
		
		System.out.println("@BeforeMethod Method");
		
		WebDriverManager.chromedriver().setup();

		// Open browser
		driver = new ChromeDriver();

		// Load url
		driver.get(url);

		// maxi
		driver.manage().window().maximize();

		// enter username
		WebElement name = driver.findElement(By.id("username"));

		name.sendKeys(userName);

		// enter password
		driver.findElement(By.id("password")).sendKeys(password);

		// Login
		driver.findElement(By.className("decorativeSubmit")).click();

		// Click CRM/SFA
		driver.findElement(By.linkText("CRM/SFA")).click();
	}

	@AfterMethod
	public void postCondition() {
		// Close browser
		System.out.println("@AfterMethod Method");
		driver.close();

	}

	@DataProvider(name = "sendData")
	public String[][] sendData() throws IOException {
		/*
		 * String [][] data = new String [1] [3];
		 * 
		 * data[0][0] = "TestLeaf"; data[0][1] = "Vidya" ; data[0][2] = "S";
		 * 
		 * return data;
		 */
		System.out.println("@DataProvider Method");

		//ExcelFamily read = new ExcelFamily();
		//read.readExcel(fileName);
		
		String[][] data = ExcelFamily.readExcel(fileName);
		
		return data;
	}
}
