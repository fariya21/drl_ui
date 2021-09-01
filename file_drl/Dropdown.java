package utilities;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseClass.BaseClass;

public class Dropdown extends BaseClass {
	public void click_on_auto_suggesstion_text_box(WebElement autosugbox)
	{
	//WebElement autosugbox=driver.findElement(By.xpath(xpath_Str));
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
}