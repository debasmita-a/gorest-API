package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {
		prop = new Properties();
		// maven : supply the env from cmd
		// mvn clean install -Denv="qa"/"QA"

		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("Running tests on default QA env..");
				ip = new FileInputStream("./src/main/resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					System.out.println("Running tests on Stage env..");
					break;
				case "dev":
					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
					System.out.println("Running tests on Dev env..");
					break;
				case "qa":
					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
					System.out.println("Running tests on QA env..");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					System.out.println("Running tests on Production env..");
					break;
				default:
					System.out.println("Please pass the right env name. " + envName);
					throw new APIFrameworkException("No ENV GIVEN");
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
}
