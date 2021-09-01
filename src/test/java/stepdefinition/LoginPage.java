package stepdefinition;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import PageClass.LogintoFileroom;
import PageClass.TaxPrrofx;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends BaseClass
{
	LogintoFileroom login;	
	
	@Given ("Launch Fileroom url")
	public void launchingwithfileroom_URL()
	{
		initialization();
		System.out.println("Launching browser succussfully.");
	}
	
	@When ("Enters login credential")
	public void enterdetails() throws Exception,Throwable
	{	
		   login = new LogintoFileroom();				
   		   login.enteruserdetails();
	}
	
	@And   ("Submit login button")
	public void clickon_Login() throws InterruptedException
	{
		login = new LogintoFileroom();
		login.clickonLogin_btn();
	}
	
	@Then  ("User  able to login successfully in Fileroom")
	public void verify_Title()
	{	
		login = new LogintoFileroom();
		login.verifyTitle_Page();
		//closebrowser();
	}
}
