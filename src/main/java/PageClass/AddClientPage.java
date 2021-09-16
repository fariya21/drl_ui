package PageClass;

import baseClass.BaseClass;
import utilities.Utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddClientPage  extends BaseClass{
	@FindBy(xpath= "//div[@role='dialog']")
	WebElement DialogBox;
	@FindBy(xpath="//span[text()='OFFLINE']")
	WebElement OfflineRadio;
	
	@FindBy(xpath="//div[@data-test='firstName-input']/div/input")
    WebElement FirstName;
	
	@FindBy(xpath="//div[@data-test='lastName-input']/div/input")
	WebElement LastName;
	
	@FindBy(xpath="//div[@data-test='email-input']/div/input")
	WebElement Email;
	
	@FindBy(xpath="//span[text()='OFFICE LOCATION']//ancestor::LABEL//following-sibling::DIV//INPUT")
	WebElement Location;
		
	@FindBy(xpath="//span[text()='OWNER']//ancestor::LABEL//following-sibling::DIV//INPUT")
	WebElement Owner;
	
	//@FindBy(xpath="//div[@data-test='taxSoftware-input']")
	//WebElement taxSoftware;
	@FindBy(xpath="//*[@id='menu-']/div[3]/ul/li[1]")
	WebElement Taxname;
	
	@FindBy(xpath="//div[@data-test='taxAccountNumber-input']")
	WebElement taxAccountNum;
	
    @FindBy(xpath="//div[@data-test='taxClientId-input']//input")
    WebElement taxClientId;
	@FindBy(xpath="//div[@data-test='taxSoftwareVersion-input']//input")
	WebElement taxSoftwareVersion;
    
	@FindBy(xpath="//SPAN[text()='SAVE']//ancestor::BUTTON")
	WebElement SAVE;	
	@FindBy(css="span._2WJ7O > div")			
	WebElement ClientPopup;
	Utility uL = new Utility();
	
	 public AddClientPage()	 
	 {
		PageFactory.initElements(driver,this);	
	 }
	 public void VerifyAddClient()
	 {
		 WebElement  element=driver.findElement(By.cssSelector("#administrativeModules > div > div > div.administrative-tabs > div > div._24V7r > div.dJkHJ > div._271eP > button:nth-child(3)"));
		element.click();
		 if(!DialogBox.isDisplayed())
		{
			System.out.println("Add Client Dialog not Available");
		}
			
	 }
	 
	 public void OfflineRadioButtonClick()
	 {
		 OfflineRadio.click();	 
	 }
	 public void AddDetailClient()throws IOException, ParseException,InterruptedException
	 {
		   // JSONParser jp = new JSONParser();
			//FileReader fr = new FileReader("C:/Users/fariya.wani/eclipse-workspace/DRL/src/main/java/TestData/ClientDetails.json");
		    //JSONObject jobject = (JSONObject) jp.parse(fr);
			//JSONArray jarray = (JSONArray) jobject.get("ClientDetails");
			List<JSONObject> Client = uL.GetJsonData(System.getProperty("user.dir") + "/src/main/java/TestData/ClientDetails.json", "ClientDetails");
			for (JSONObject ClientDet :Client)
			{
			//JSONObject ClientDet = (JSONObject) jarray.get(0);
			String First = (String) ClientDet.get("FirstName");
		    String Last =(String)ClientDet.get("LastName");
		    String EmailAdd =(String)ClientDet.get("Email");
		    String Loc=(String)ClientDet.get("Location");
		    String Own=(String)ClientDet.get("Owner");
		    String TaxSoft=(String)ClientDet.get("TaxSoftware");
		    String TaxAccountNumber=(String)ClientDet.get("TaxAccountNumber");
		    String TaxClID=(String)ClientDet.get("TaxClientID");
		    String ver=(String)ClientDet.get("SoftwareVersion");  		    		    
		    
			FirstName.sendKeys(First);
			LastName.sendKeys(Last);
			Email.sendKeys(EmailAdd);
			//for location			
			Utility dd= new Utility();
			dd.click_on_auto_suggesstion_text_box(Location);
			dd.enter_just_Str("New Y", Location);
			dd.select_required_from_auto_suggestion("//div[5]/div/ul",Loc);
			
			dd.click_on_auto_suggesstion_text_box(Owner);
			dd.enter_just_Str("vidya", Owner);
			dd.select_required_from_auto_suggestion("//div[5]/div/ul",Own);
			
			//dd.click_on_auto_suggesstion_text_box(taxSoftware);
		
			Taxname.click();
			Taxname.sendKeys(TaxSoft);
			//dd.enter_just_Str("CCH", taxAccountNum);
			dd.click_on_auto_suggesstion_text_box(taxAccountNum);			
			taxClientId.sendKeys(TaxClID);
			taxSoftwareVersion.sendKeys(ver);
			SAVE.click();
			}
	 }
	
public void VerifyClient()
{
	if(ClientPopup.isDisplayed())
		System.out.println("Client Added");
}
} 
