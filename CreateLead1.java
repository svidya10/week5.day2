package week5.day2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLead1 extends BaseClass1{
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("@BeforeClass Method");
		fileName = "CreateLead";
	}
	
	@Test (dataProvider = "sendData", priority = 1)
	public void createLead(String cName, String fName, String lName, String country) {
		
		System.out.println("@Test Create Lead Method");

		// Click Contact tab
		driver.findElement(By.linkText("Contacts")).click();
		// Create Contact
		driver.findElement(By.linkText("Create Contact")).click();
		// Enter first name
		WebElement firstname = driver.findElement(By.id("firstNameField"));
		firstname.sendKeys(fName);
		System.out.println(firstname.getAttribute("value"));
		// Enter last name
		WebElement lastname = driver.findElement(By.id("lastNameField"));
		lastname.sendKeys(lName);

		System.out.println(lastname.getAttribute("value"));

		// Click Create Contact Button
		driver.findElement(By.className("smallSubmit")).click();

		// Print First Name and Title
		String firstNameText = driver.findElement(By.id("viewContact_firstName_sp")).getText();
		System.out.println("First name string is : " + firstNameText);

		String title = driver.getTitle();
		System.out.println("Browser title is : " + title);

	}
	

	
}

