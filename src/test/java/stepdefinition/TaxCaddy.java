package stepdefinition;

import PageClass.TaxCaddyPage;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaxCaddy extends BaseClass
{
	TaxCaddyPage tc;
	
	@Given ("^User clicking on Administrative button from TaxCaddy page$")
	public void clickon_Admin()
	{
		tc = new TaxCaddyPage();
		tc.clickon_Admintab();
	}
	
	@And ("^Verify allow user to move on Administrative page$")
	public void verify_Titleofpage() throws InterruptedException
	{
		tc = new TaxCaddyPage();
		tc.verify_TCAdmin_Title();
	}
	
	@When ("^Clicking on Add Client button$")
	public void addonclient()
	{
		tc = new TaxCaddyPage();
		tc.clickon_AddClientBtn();
	}
	
	@Then ("^Allow user to open Add client Pop-up Window$")
	public void verify_AddClientWindow()
	{
		System.out.println("Add Client Window open successfully.");
	}
}
