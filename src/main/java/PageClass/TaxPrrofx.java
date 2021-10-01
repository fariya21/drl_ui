package PageClass;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

import baseClass.BaseClass;
import io.cucumber.datatable.DataTable;
import okhttp3.internal.http2.Header;
import utilities.TestUtil;
import winium.elements.desktop.DataGrid;

public class TaxPrrofx extends BaseClass{
	public String File_Download(String fileName)throws Exception
	{
	      File path= new File("//sp-useprosrv.sp.com/Citrix-UD/fariya.wani/Downloads");
	     
	      File url=waitForDownloadToComplete(path, fileName);   
	      String filePath=url.getPath();
	      desktopInitialization(filePath);
	      Thread.sleep(45000);
	      WebElement uploader= windriver.findElement(By.name("TaxCaddy Tax File Uploader"));	 
	      WebDriverWait wait = new WebDriverWait(driver,TestUtil.Explicit_WAIT);
		  wait.until(ExpectedConditions.visibilityOf(uploader));
	      WebElement  dt=BaseClass.windriver.findElement(By.id("UploadFilesDataGrid"));//
	      Thread.sleep(10000);
	      List<WebElement> rows = dt.findElements(By.className("DataGridRow"));
	       //List<WebElement> columnsList = null;
	      WebElement columnList=dt.findElement(By.className("DataGridColumnHeadersPresenter"));
	      int Status=0,RequestID=0,counter=0;
	      List<WebElement> header=columnList.findElements(By.className ("DataGridColumnHeader"));
	      for (WebElement column : header) {
	    	   if(column.getAttribute("Name").equals("Request ID"))
	    		   RequestID=header.indexOf(column);
	    	   else if (column.getAttribute("Name").equals("Status")) {
				Status=header.indexOf(column);			}
	           
	         }	      	     
	        //for ( int i=0;i<rows.size()-1;i++) {
    	   	List<WebElement> cells= rows.get(0).findElements(By.className("DataGridCell"));	  
    	   	String status= cells.get(Status).getText();
    	   	if(status.contains("Started")&& counter>=10)
    	   	{    	  
    	   	   Thread.sleep(5000);
	           counter ++;
    	   	}
    	   	else
    	   	{
    	    status= cells.get(Status).getText();
    	   	System.out.println(status);
	        System.out.println(cells.get(RequestID).getText());
    	   	}
	     //  }
	        //use executeScript() method and pass the arguments 
	        //Here i pass values based on css style. Yellow background color with solid red color border. 
	       //js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", dt);	       
           
           //Runtime.getRuntime().exec(filePath);
           //desktopInitialization(filePath);
           //return filePath;
           //desktopInitialization(filePath);
           WebElement close= windriver.findElement(By.id("CloseButton"));
           close.click();
           return filePath;

	}
	public static File waitForDownloadToComplete(File downloadPath, String fileName) throws Exception {
       		
		boolean isFileFound = false;
        int waitCounter = 0;
        while (!isFileFound) {
            for (File tempFile : downloadPath.listFiles()) {
                if (tempFile.getName().contains(fileName)) {
                    String tempEx = tempFile.getName();
                    // crdownload - For Chrome, part - For Firefox
                    if (tempEx.equalsIgnoreCase("crdownload") || tempEx.equalsIgnoreCase("part")) {
                        Thread.sleep(1000);
                    } else {
                        isFileFound = true;
                        System.out.println("File opened");
                       // URI url=tempFile.toURI();
                        return tempFile;
                        //Runtime.getRuntime().exec(tempFile.getPath());
                       // windriver.get(tempEx);
                    }
                }
            }
            Thread.sleep(1000);
            waitCounter++;
            if (waitCounter > 25) {
                isFileFound = true;
            }
        }
        throw new Exception("File Not Downloaded");
    }
public void GetWindow()
{


	
}
}
