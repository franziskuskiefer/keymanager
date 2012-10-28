package de.franziskuskiefer.keymanager.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

	public static Properties prop = new Properties();
	static{
		try {
			prop.load(new FileInputStream("src/de/franziskuskiefer/pgp/keymanager/keymanager.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
