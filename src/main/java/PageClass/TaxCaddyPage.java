package PageClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseClass;

import utilities.TestUtil;
import utilities.Utility;
import utilities.DatabaseConnection;

public class TaxCaddyPage extends BaseClass {
	@FindBy(id = "admin-tab")
	WebElement AdminTab;

	@FindBy(css = "button[title='Add a client']")
	WebElement Add_ClientBtn;

	@FindBy(css = "[aria-label='close']")
	WebElement PopUp_CloseBtn;

	@FindBy(xpath = "//div[@role='dialog']")
	WebElement DialogBox;
	@FindBy(xpath = "//span[text()='OFFLINE']")
	WebElement OfflineRadio;

	@FindBy(xpath = "//div[@data-test='firstName-input']/div/input")
	WebElement FirstName;

	@FindBy(xpath = "//div[@data-test='lastName-input']/div/input")
	WebElement LastName;

	@FindBy(xpath = "//div[@data-test='email-input']/div/input")
	WebElement Email;

	@FindBy(xpath = "//span[text()='OFFICE LOCATION']//ancestor::LABEL//following-sibling::DIV//INPUT")
	WebElement Location;

	@FindBy(xpath = "//span[text()='OWNER']//ancestor::LABEL//following-sibling::DIV//INPUT")
	WebElement Owner;

	@FindBy(xpath = "//div[@data-test='taxSoftware-input']")
	WebElement taxSoftware;
	
	@FindBy(xpath = "//*[@id='menu-']/div[3]/ul")
	WebElement Taxname;

	@FindBy(xpath = "//div[@data-test='taxAccountNumber-input']")
	WebElement taxAccountNum;

	@FindBy(xpath = "//div[@data-test='taxClientId-input']//input")
	WebElement taxClientId;
	@FindBy(xpath = "//div[@data-test='taxSoftwareVersion-input']//input")
	WebElement taxSoftwareVersion;

	@FindBy(xpath = "//SPAN[text()='SAVE']//ancestor::BUTTON")
	WebElement SAVE;
	@FindBy(css = "span._2WJ7O > div")
	WebElement ClientPopup;

	@FindBy(css = "input[id]")
	WebElement SearchTextBox;

	@FindBy(css = "button[class*='manageButton']")
	WebElement TC_InvitationBtn;

	@FindBy(css = "[title='Create Custom DRL']")
	WebElement Custom_DRLBtn;

	@FindBy(xpath = "//input[contains(@class, 'jss')]")
	WebElement Selectrecord_Chkbox;

	@FindBy(css = "[title='Refresh the client list']")
	WebElement Refresh_btn;

	@FindBy(css = "button[aria-label='refresh']")
	WebElement Refresh_PopupBtn;

	//@FindBy(css = "div[class*='StatusBadge']")
	@FindBy(css = "div[class*='_3kIv4']")
	WebElement DRL_Status;
	
	@FindBy(xpath = "//button[contains(@class,'2xgZv MuiButton-containedPrimary')]")
	WebElement DRL_PopBtn;
	
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	Utility ul = new Utility();

	public static String  EmailID, First, Last, Loc, Own, TaxSoft, TaxAccountNumber, ClientID, ver;
	public static int index,Counter=0;
	List<JSONObject> jcred;
	Boolean flagAdd= true;
	public TaxCaddyPage()
	{
		PageFactory.initElements(driver, this);
	}

	public void clickon_Admintab()
	{
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.Explicit_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(AdminTab));
		AdminTab.click();
	}

	public void verify_TCAdmin_Title() throws InterruptedException
	{
		String ExpectedTitle = "Administrative - TaxCaddy CPA";
		Thread.sleep(12000);
		String ActualTitle = driver.getTitle();
		System.out.print(ActualTitle);
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	}

	public void clickon_AddClientBtn() throws Exception, IOException
	{
		
		if (PopUp_CloseBtn.isEnabled())
			PopUp_CloseBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.Explicit_WAIT);		
		wait.until(ExpectedConditions.elementToBeClickable(Add_ClientBtn));        
    	executor.executeScript("arguments[0].click();", Add_ClientBtn);
		//Add_ClientBtn.click();
		//flagAdd =false;
		
	}

	public void VerifyAddClient()
	{		
		if (DialogBox.isDisplayed())
			System.out.println("Add Client Dialog Available");
		else
			System.out.println("Add Client Dialog not Available");
	}

	public void OfflineRadioButtonClick()
	{
		OfflineRadio.click();
	
	}

	public void AddDetailClient() throws Throwable
	{
		jcred = ul.GetJsonData(System.getProperty("user.dir") + "/src/main/java/TestData/ClientDetails.json", "ClientDetails");	
		JSONObject jo ;
		for (int i=0;i<=jcred.size()-1 ;i++)//JSONObject//
		{
		    jo = jcred.get(i);
			First = (String) jo.get("FirstName");
			Last = (String) jo.get("LastName");
			EmailID = (String) jo.get("Email");
			Loc = (String) jo.get("Location");
			Own = (String) jo.get("Owner");
		    index = Integer.parseInt( jo.get("TaxSoftware").toString());			
			TaxAccountNumber = (String) jo.get("TaxAccountNumber");
			ClientID = (String) jo.get("TaxClientID");
			ver = (String) jo.get("SoftwareVersion");
			FirstName.sendKeys(First);
			LastName.sendKeys(Last);
			Email.sendKeys(EmailID);
			ul.click_on_auto_suggesstion_text_box(Location);
			ul.enter_just_Str("New Y", Location);
			ul.select_required_from_auto_suggestion("//div[5]/div/ul", Loc);
			ul.click_on_auto_suggesstion_text_box(Owner);
			ul.enter_just_Str("vidya", Owner);
			ul.select_required_from_auto_suggestion("//div[5]/div/ul", Own);
			
			//ul.click_on_auto_suggesstion_text_box(taxSoftware);
			Actions builder = new Actions(driver);
	        builder.moveToElement( taxSoftware ).click( taxSoftware );
	        builder.perform();		
	    	//executor.executeScript("arguments[0].click();", taxSoftware);
			//taxSoftware.click();
			Thread.sleep(3000);
			TaxSoft=ul.EnumTax(index);
			getTaxName(TaxSoft);
			// dd.enter_just_Str("CCH", taxAccountNum);
			//ul.click_on_auto_suggesstion_text_box(taxAccountNum);
			taxClientId.click();
			taxClientId.sendKeys(ClientID);		
		    taxSoftwareVersion.sendKeys(ver);
		    SAVE.click();
		    if (i<jcred.size()-1)
		    {		    	
		    	VerifyClient();
		    	Thread.sleep(6000);
		    	enter_Text(EmailID);
			    select_ChkBox();
		     	create_DRL();
				verify_DRLStatus();
				Thread.sleep(10000);	
		        builder.moveToElement( Add_ClientBtn ).click( Add_ClientBtn );
		        builder.perform();		    
		 		//Add_ClientBtn.click();		 		
		    	OfflineRadioButtonClick();		    					
		    
		    }
		    System.out.println("Client no"+i+" Added");
		}
		
	}
	 public void getTaxName(String OptionName)
	 {		
	    List<WebElement> options = Taxname.findElements(By.tagName("li"));
	    for (WebElement option:options)
	    {	  
	    	//System.out.println(option.getText());
	    	if(option.getText().contains(OptionName))
	    	{	    	
	    		option.click();
	    		break;
	    	}
	    }
	 }
	public void VerifyClient()
	{
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.Explicit_WAIT);
		wait.until(ExpectedConditions.visibilityOf(ClientPopup));
		if (ClientPopup.isDisplayed())
			System.out.println("Client Added");
		
	}

	public void enter_Text( String email) throws Exception
	{		
		//List<JSONObject> jcred = ul.GetJsonData(System.getProperty("user.dir")+"/src/main/java/TestData/clientDetails.json", "ClientDetails");
		//JSONObject jo ;
		//for (int i=0;i<=jcred.size()-1 ;i++)//JSONObject//
		//{
	    //jo = jcred.get(0);
		//EmailID = (String) jo.get("Email");
		Actions builder = new Actions(driver);
        builder.moveToElement( Refresh_PopupBtn ).click(Refresh_PopupBtn );
        builder.perform();
        Thread.sleep(6000);
        //SearchTextBox.click();
        SearchTextBox.clear();
        SearchTextBox.sendKeys(Keys.CONTROL + "a");
        SearchTextBox.sendKeys(Keys.DELETE);
        SearchTextBox.sendKeys(Keys.BACK_SPACE,email);        
        //Refresh_PopupBtn.click();
		//SearchTextBox.sendKeys(EmailID);
		Thread.sleep(10000);		
	//	}
		
	}

	public boolean select_ChkBox() throws InterruptedException
	{
		Boolean flag;
		//JavascriptExecutor executor = (JavascriptExecutor) driver;

		List<WebElement> chk_box_List = driver.findElements(By.xpath("//input[contains(@class, 'jss')]"));
		int count_CheckBox = chk_box_List.size();
		for (int a = 0; a < count_CheckBox; a++) {

			Thread.sleep(8000);
			flag = Selectrecord_Chkbox.isSelected();
			if (flag != true && Selectrecord_Chkbox.isEnabled())
			{
				executor.executeScript("arguments[0].click();", Selectrecord_Chkbox);				
				break;
			}
		}
		return Selectrecord_Chkbox.isSelected();
	}

	public void create_DRL() throws InterruptedException 
	{
		TC_InvitationBtn.click();
		Thread.sleep(2000);
		Custom_DRLBtn.click();
		Thread.sleep(3000);
		select_ChkBox();
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.Explicit_WAIT);
		wait.equals(ExpectedConditions.elementToBeClickable(DRL_PopBtn));
		if(DRL_PopBtn.isEnabled())
		{
		DRL_PopBtn.click();
		}
		Thread.sleep(5000);
	}
	
	public void verify_DRLStatus() throws Exception
	{
				
		String status = "Completed";
		String status1 = "In Error";
		String Tax =ul.EnumTax(index);
		if ((Tax.contains("ProSystem"))||(Tax.contains("Lacerte"))||(Tax.contains("Ultra")))
		{
			Thread.sleep(20000);
			TaxPrrofx profx= new TaxPrrofx();
			profx.File_Download("TaxCaddy - TaxFileUpload.pbfx");
								
		}
		else {
		for(int i=0; i<30; i++)
		{
			String aa = DRL_Status.getAttribute("title");
			System.out.println(aa);
			//System.out.println(i);
			if (status.contains(aa) || status1.contains(aa))
			{			
				System.out.println("Status of DRL Request is: " + aa);
				Thread.sleep(10000);
				DatabaseConnection db_conn = new DatabaseConnection();
				db_conn.databaseConnection(Counter);
				break;				
			}			
			else
			{
				executor.executeScript("arguments[0].click();", Refresh_btn);				
				Thread.sleep(10000);
				
			}			
		} 
		}
		Counter++;
		//BaseClass.closebrowser();
	}
}
