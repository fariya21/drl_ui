package stepdefinition;

import PageClass.FileRoomPage;
import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Fileroom extends BaseClass
{
	FileRoomPage fileroom;
	
	@Given ("^User mouseover on dropdown list option$")
	public void mouseoveronlistoptions() throws InterruptedException
	{
	  fileroom = new FileRoomPage();
	  fileroom.mouseoveron_ListOptions();
	}
	
	@When("^Clicking on TaxCaddy option$")
	public void clickon_TCoption()
	{
		fileroom = new FileRoomPage();
		fileroom.clickingon_TC();
	}
	
	@Then ("^Allow user to navigate on TaxCaddy page$")
	public void verify_TCTitle()
	{
		fileroom = new FileRoomPage();
		fileroom.verify_TC_title();
		//System.out.println("TaxCaddy page has launched successfully.");
	}
}
