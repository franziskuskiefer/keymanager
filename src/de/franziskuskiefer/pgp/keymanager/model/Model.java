package de.franziskuskiefer.pgp.keymanager.model;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.franziskuskiefer.pgp.keymanager.util.Constants;
import static de.franziskuskiefer.pgp.keymanager.util.PropertyHelper.prop;

public class Model implements Constants {

	KeyPairs keys;
	
	public Model() {
		// read config
	    try {
	    	JAXBContext context = JAXBContext.newInstance(KeyPairs.class);
	    	Unmarshaller um = context.createUnmarshaller();
			keys = (KeyPairs) um.unmarshal(new FileReader(prop.getProperty(CONFIG_FILE)));
		} catch (FileNotFoundException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public KeyPairs getKeys() {
		return keys;
	}
	
}
