package stepdefinition;
import baseClass.BaseClass;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;

import PageClass.AddClientPage;
import PageClass.TaxCaddyPage;
import baseClass.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddClientPopUp extends BaseClass{
	
	AddClientPage CLpage ;
	
	@Given("Verify Add Client PopUP")
	public void VerifyAddClientPOPup()
	{   
		//initialization();
		CLpage= new AddClientPage();
		CLpage.VerifyAddClient();
	}
	
    @When("Click Offline Button")
    public void ClickOffline()
    {
    	CLpage= new AddClientPage();
    	CLpage.OfflineRadioButtonClick();
    }
  @And("Enter Client Detail")
  public void AddClientInformation()throws IOException, ParseException,InterruptedException
  {
	  CLpage= new AddClientPage();
	  CLpage.AddDetailClient();
  }
  @Then("Client is Added")
  public void VerifyClientAdded() 
  {
	  CLpage= new AddClientPage();
	  CLpage.VerifyClient();
  }
}
