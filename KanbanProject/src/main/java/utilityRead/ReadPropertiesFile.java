package utilityRead;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesFile 
{
	
	static Properties prop;
	public	String readPropertiesConfig(String url) {
			try
			{
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/configuration/config.properties");
						
						
				prop = new Properties();
				prop.load(file);
			}
			catch (Exception e)
			{
				System.out.println("File not found");
			}
			String value = prop.getProperty(url);
			return value;
		}

}
