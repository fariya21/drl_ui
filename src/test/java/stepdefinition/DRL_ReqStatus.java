package stepdefinition;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;

import PageClass.TaxCaddyPage;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DRL_ReqStatus extends BaseClass
{
	TaxCaddyPage tc;
	
	@Given ("^Enter Client ID in searchbox$")
	public void enter_clientId() throws Exception
	{
		tc = new TaxCaddyPage();
		tc.enter_Text(TaxCaddyPage.EmailID);
	}

	@And ("^Select record for DRL request$")
	public void click_ChkBox() throws InterruptedException
	{
		tc = new TaxCaddyPage();
		tc.select_ChkBox();
	}
	
	@When ("^Click Create Custom DRL$")
	public void click_DRL() throws InterruptedException
	{
		tc = new TaxCaddyPage();
		tc.create_DRL();
	}
	
	@Then ("^Verify Status changed to Completed or In Error$")
	public void verify_Status() throws Exception
	{
		tc = new TaxCaddyPage();
		tc.verify_DRLStatus();
		BaseClass.closebrowser();
	}
	
}
