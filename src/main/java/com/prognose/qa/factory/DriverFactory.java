package com.prognose.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

	public class DriverFactory {
		
		WebDriver driver;
		Properties prop;	// Coming from java
		OptionsManager optionsManager;
		public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
		
		
		
		
		/**
		 * Initialize the driver
		 * @param browserName
		 * @return
		 */
		public WebDriver initDriver(Properties prop) {
			String browserName = prop.getProperty("browser");
			
			optionsManager = new OptionsManager(prop);
			
			switch (browserName.toLowerCase()) {
			case "chrome":
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set( new FirefoxDriver(optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				break;
			case "safari":
				tlDriver.set(new SafariDriver());
				break;
			default:
				System.out.println("Please pass the right browser" + browserName);
				break;
			}
			
			getDriver().manage().window().maximize();
			getDriver().manage().deleteAllCookies();
//			getDriver().get(prop.getProperty("url"));
			return getDriver();
		}
		
		public static WebDriver getDriver() {
			return tlDriver.get();
			
		}
		
		/**
		 * to make connection with properties file / To initialize the properties
		 * @return
		 */
		
		public Properties initProp() {

			// mvn clean install -Denv="qa"
			FileInputStream ip = null;
			prop = new Properties();

			String envName = System.getProperty("env");
			System.out.println("env name is : " + envName);

			try {
				if (envName == null) {
					System.out.println("no env is given...hence running it on beta env..by default");
					ip = new FileInputStream("./src/test/resource/config/config.properties");
				} else {

					switch (envName.toLowerCase().trim()) {
					case "qa":
						ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
						break;
					case "dev":
						ip = new FileInputStream("./src/test/resource/config/dev.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resource/config/stage.config.properties");
						break;
					case "uat":
						ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
						break;
					case "prod":
						ip = new FileInputStream("./src/test/resource/config/config.properties");
						break;
					case "beta":
						ip = new FileInputStream("./src/test/resource/config/beta.config.properties");

					default:
						System.out.println("please pass the right env name...." + envName);
						break;
					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
		}
		
		/**
		 * TakesScreenshot
		 * @return
		 */
		
		public static String getScreenshot(String methodName) {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\screenshot\\" + methodName + "_" + System.currentTimeMillis() + ".png";

			System.out.println(path);
			File destFile = new File(path);
			
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return path;
		}

}