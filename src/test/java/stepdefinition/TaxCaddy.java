package stepdefinition;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import PageClass.TaxCaddyPage;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Utility;

public class TaxCaddy extends BaseClass
{
	TaxCaddyPage tc;
	
	Utility ul = new  Utility();
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
	public void addonclient() throws ParseException, IOException,Throwable
	{
		tc = new TaxCaddyPage();
		//List<JSONObject> jcred = ul.GetJsonData(System.getProperty("user.dir") + "/src/main/java/TestData/ClientDetails.json", "ClientDetails");
		tc.clickon_AddClientBtn();
	}
	
	@Then ("^Allow user to open Add client Pop-up Window$")
	public void verify_AddClientWindow()
	{
		System.out.println("Add Client Window open successfully.");
	}
}
