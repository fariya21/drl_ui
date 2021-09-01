package PageClass;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;
import utilities.DatabaseConnection;
import utilities.Utility;

public class LogintoFileroom extends BaseClass
{
	@FindBy(id= "DomainName")
	WebElement Domain;
	
	@FindBy(id= "UserName")
	WebElement User;
	
	@FindBy(id= "Password")
	WebElement Pass;
	
	@FindBy(id= "btnLogin")
	WebElement Loginbtn;
	
	Utility uL = new Utility();	
	String domain, user, pass;

	public LogintoFileroom()
	{
		
		PageFactory.initElements(driver,this);
	}
	
	public void enteruserdetails() throws Throwable,Exception
	{
		List<JSONObject> jcred = uL.GetJsonData(System.getProperty("user.dir") + "/src/main/java/TestData/logincred.json", "FormDetails");
		/*
		 * JSONParser jp = new JSONParser(); FileReader fr=new FileReader(
		 * System.getProperty("user.dir")+"/src/main/java/TestData/logincred.json");
		 * JSONObject jobject = (JSONObject) jp.parse(fr); JSONArray jarray =
		 * (JSONArray) jobject.get("FormDetails"); JSONObject cred = (JSONObject)
		 * jarray.get(0);
		 */
		    
		    JSONObject jo = jcred.get(0);
			domain = (String) jo.get("DomainName");
			user = (String) jo.get("Username");
			pass = (String) jo.get("Password");
			Domain.sendKeys(domain);
			User.sendKeys(user);
			Pass.sendKeys(pass);				
	}
	
	public void clickonLogin_btn() throws InterruptedException
	{
		
		Loginbtn.click();
		Thread.sleep(10000);
	}
	
	public void verifyTitle_Page()
	{
		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "FileRoom";
		String ExpectedTitle1 = "User Management";
		System.out.println(ActualTitle);
		if(ActualTitle.equalsIgnoreCase(ExpectedTitle))
		   Assert.assertEquals(ExpectedTitle, ActualTitle);		
		else
			Assert.assertEquals(ExpectedTitle1, ActualTitle);
		
		
	}
}
