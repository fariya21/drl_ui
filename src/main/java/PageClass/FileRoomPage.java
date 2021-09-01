package PageClass;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseClass;
import utilities.TestUtil;

public class FileRoomPage extends BaseClass 
{
	@FindBy(xpath = "//div[@class='inner-div']")
	WebElement ListOptions;
	
	@FindBy(xpath="//a[@href='#' and @onclick='TaxCaddy()']")
	WebElement TaxCaddy;
	
	@FindBy(id="admin-tab")
    WebElement AdminTab;
	
	public FileRoomPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void mouseoveron_ListOptions() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(ListOptions).perform();;
		Thread.sleep(2000);
	}
	
	public void clickingon_TC()
	{
		TaxCaddy.click();
	}
	
	public void verify_TC_title()
	{
		String ExpectedTitle = "Overview - TaxCaddy CPA";
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.Explicit_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(AdminTab)); 
		//Thread.sleep(45000);
		String ActualTitle = driver.getTitle();
		System.out.println(ActualTitle);
		Assert.assertEquals(ExpectedTitle, ActualTitle);
				
	}
	

}
