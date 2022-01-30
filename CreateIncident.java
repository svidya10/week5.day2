package week5.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateIncident extends BaseClass2 {
	@BeforeClass
	public void beforeClass() {
		System.out.println("@BeforeClass Method");
		fileName = "CreateIncident";
	}

	@Test(dataProvider = "sendData", priority = 1)
	public void createIncident(String description) throws InterruptedException {
		System.out.println("@Test Create Incident Method");
		// click new
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		// Select a value for Caller and Enter value for short_description
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		Thread.sleep(3000);
		//// Switch to child frame
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//table[@id='sys_user_table']//tr[1]/td[3]/a")).click();
		//// Switch to parent frame
		windowHandles = driver.getWindowHandles();
		windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(0));
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys(description);
		// Verify the newly created incident ( copy the incident number and paste it in
		// search field and enter then verify the instance number created or not)
		// Read the incident number and save it a variable
		String incidentNo = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println(incidentNo);
		// Click on Submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		// Search the same incident number in the next search screen as below
		driver.findElement(By.xpath(
				"//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input"))
				.sendKeys(incidentNo);
		driver.findElement(By.xpath(
				"//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input"))
				.sendKeys(Keys.ENTER);
		// Verify the incident is created successful
		String searchIncidentNo = driver.findElement(By.xpath("//table[@id='incident_table']//tr[1]/td[3]")).getText();
		System.out.println(searchIncidentNo);
		if (incidentNo.equals(searchIncidentNo) == true) {
			System.out.println("incident number present");
		} else {
			System.out.println("incident number not present");
		}
	}

}
