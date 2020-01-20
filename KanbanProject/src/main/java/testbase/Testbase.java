package testbase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilityRead.ReadPropertiesFile;

public class Testbase 
{
	protected static WebDriver driver;
	static ReadPropertiesFile readProp=new ReadPropertiesFile();
	
	public static void intialization1() throws MalformedURLException
	{
	// Create object of DesiredCapabilities class
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+"/src/main/java/driver/chromedriver.exe");
		
	//	C:\Users\swatig\git\repository\KanbanProject\src\main\java\driver\chromedriver.exe
	DesiredCapabilities dc = DesiredCapabilities.chrome();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("disable-infobars");
	dc.setCapability(ChromeOptions.CAPABILITY, options);
	
	 String host = readProp.readPropertiesConfig("host");// Host needs to change as perrequirement
	 driver = new RemoteWebDriver(new URL("http://" + host +":4444/grid/register"), dc); // new URL needs to change as per // requiremnet

//	System.setProperty("webdriver.chrome.driver",
//			"C:\\Users\\swatig\\eclipse-workspace\\KanbanProject\\src\\main\\java\\driver\\chromedriver.exe");
//	driver = new ChromeDriver(options);

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get(readProp.readPropertiesConfig("appURL"));
	driver.manage().window().maximize();
	}

	

}
