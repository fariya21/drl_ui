package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import utilities.TestUtil;

public class BaseClass
{
	public static WebDriver driver;
	public static Properties prop;
	public static String project_location;
	public static WiniumDriver windriver;
	public static String printCurrentWorkingDirectory1()
	{
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length()-1);
        System.out.println(path);
        return path;
        }
	public BaseClass()
	{
		try
		{
			prop=new Properties();
			//FileInputStream fis=new FileInputStream("C:/Workspace/DRL/src/main/java/config.properties");
			FileInputStream fis=new FileInputStream( System.getProperty("user.dir")+"/src/main/java/config.properties");
			prop.load(fis);
	}
		catch(IOException e)
		
		{
			e.getMessage();
			
		}
	}
	
	public static void initialization()
	{
		
	String browsername=prop.getProperty("Browser");
	project_location = prop.getProperty("ProjectPath");
	
	if(browsername.equals("chrome"))
			{
		System.setProperty("webdriver.chrome.driver","C:/chromedriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		// Instantiate the chrome driver
		driver = new ChromeDriver(options);
		//driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		//driver =new ChromeDriver();
		
			}
	else if(browsername.equals("firefox"))
	{
System.setProperty("webdriver.chrome.driver","C:/chromedriver/chromedriver.exe");
driver =new FirefoxDriver();

	}
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);	
	driver.get(prop.getProperty("Url"));
	}
	public static void desktopInitialization(String path) throws MalformedURLException
    {
     String appPath = path;//"C:/Program Files (x86)/SurePrepLLC/PBFX/SurePrep.PortableBinderFormat(PBF).exe";
     DesktopOptions option = new DesktopOptions();
     option.setApplicationPath(appPath);
     option.setDebugConnectToRunningApp(false);
     option.setLaunchDelay(8);
     windriver = new WiniumDriver(new URL("http://localhost:9999"),option); 
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

	public static void closebrowser()
	{
		driver.quit();
	}

}
