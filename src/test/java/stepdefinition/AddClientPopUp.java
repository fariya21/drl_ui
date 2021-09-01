package stepdefinition;
import baseClass.BaseClass;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;


import PageClass.TaxCaddyPage;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddClientPopUp extends BaseClass{
	
	TaxCaddyPage tc;
	
	@Given("Verify Add Client PopUP")
	public void VerifyAddClientPOPup()
	{   
		//initialization();
		tc= new TaxCaddyPage();
		tc.VerifyAddClient();
	}
	
    @When("Click Offline Button")
    public void ClickOffline()
    {
    	tc= new TaxCaddyPage();
    	tc.OfflineRadioButtonClick();
    }
  @And("Enter Client Detail")
  public void AddClientInformation()throws IOException, ParseException,InterruptedException
  {
	  tc= new TaxCaddyPage();
	  tc.AddDetailClient();
  }
  @Then("Client is Added")
  public void VerifyClientAdded() 
  {
	  tc= new TaxCaddyPage();
	  tc.VerifyClient();
  }
}
