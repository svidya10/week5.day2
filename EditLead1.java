package week5.day2;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import week2.day2.EditLead;

public class EditLead1 extends BaseClass1 {

	@BeforeClass
	public void editLeadFile() {
		System.out.println("@BeforeClass Method");

		fileName = "EditLead";
	}

	@Test(dataProvider = "sendData")
	public void editLead(String fName, String cName) throws InterruptedException {
		
		System.out.println("@Test Edit Lead Method");
		
		// Click Leads Link
		driver.findElement(By.linkText("Leads")).click();

		// Click Find Leads
		driver.findElement(By.linkText("Find Leads")).click();

		// Enter first name
		driver.findElement(By.xpath("(//div[@class='x-form-element']/input[@name='firstName'])[3]")).sendKeys((fName));

		// Click find leads button
		driver.findElement(By.xpath("//button[text() = 'Find Leads']")).click();

		Thread.sleep(1000);

		// Click First Resulting lead
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-firstName']/a")).click();

		// Verify the title of page
		System.out.println(driver.getTitle());

		// Click Edit
		driver.findElement(By.linkText("Edit")).click();

		// Change the company name
		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys((cName));

		// Click Update
		driver.findElement(By.className("smallSubmit")).click();

		// Confirm the changed name appears
		if (driver.findElement(By.id("viewLead_companyName_sp")).getText().contains("Selenium"))
			System.out.println("Changed company name appears");
		else
			System.out.println("Changed company name does not appear");

	}

}
