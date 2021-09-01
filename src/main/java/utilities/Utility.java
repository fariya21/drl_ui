package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseClass.BaseClass;

public class Utility extends BaseClass
{
	public void click_on_auto_suggesstion_text_box(WebElement autosugbox)
	{
	  	  autosugbox.click();
	}
	
	public void enter_just_Str(String partial,WebElement autosugbox)
	{
	 //driver.manage().window().maximize();
	 autosugbox.sendKeys(partial);
	}
	
	public void select_required_from_auto_suggestion(String divList,String reqdValue) throws InterruptedException
	{
	Thread.sleep(3000);
	List<WebElement> listItems=driver.findElements(By.xpath(divList)); 

	System.out.println(listItems.size());

	for (int i=0;i<listItems.size();i++)
	{
    System.out.println(listItems.get(i).getText());
	if(listItems.get(i).getText().equals(reqdValue))
	{	
		listItems.get(i).click();
	break;
	}
	}
	}
	
	public List<JSONObject> GetJsonData(String JSONpath,String objectDetail ) throws ParseException, IOException
    {
        JSONParser jp = new JSONParser();
        FileReader fr = new FileReader(JSONpath);
        JSONObject jobject = (JSONObject) jp.parse(fr);
        JSONArray jsonArray = (JSONArray) jobject.get(objectDetail);
        
          List<JSONObject> listdata=new LinkedList<JSONObject>() ;
          
            //Checking whether the JSON array has some value or not  
            if (jsonArray != null) {                     
                //Iterating JSON array  
                for (int i=0;i<jsonArray.size();i++){   
                      
                    listdata.add( (JSONObject) jsonArray.get(i));
                }   
            }  
        
        return listdata;
    }

	public String  EnumTax(int  index)
	{  
		Taxsoftwares  tax=Taxsoftwares.values()[index];		

		String TNAME="";
		switch ( tax) {
		case CCH:
			TNAME=tax.label;
		case Go:
			TNAME=tax.label;
		case Pro:
			TNAME=tax.label;
	    case Ultra:
	    	TNAME=tax.label;
	    case Others:
	    	TNAME=tax.label;
		default:
			break;
		}
		return TNAME;
	}
}
