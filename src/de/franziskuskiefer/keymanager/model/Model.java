package de.franziskuskiefer.keymanager.model;

import static de.franziskuskiefer.keymanager.util.PropertyHelper.prop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.franziskuskiefer.keymanager.util.Constants;

public class Model implements Constants {

	KeyPairs keys;
	private String config;
	private JAXBContext context;
	
	public Model() {
		// read config
		config = prop.getProperty(CONFIG_FILE);
	    try {
	    	context = JAXBContext.newInstance(KeyPairs.class);
	    	Unmarshaller um = context.createUnmarshaller();
			keys = (KeyPairs) um.unmarshal(new FileReader(config));
		} catch (FileNotFoundException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveKeys(){
		try {
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(keys, System.out);

			Writer w = null;
			try {
				w = new FileWriter(config);
				m.marshal(keys, w);
			} finally {
				try {
					w.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public KeyPairs getKeys() {
		return keys;
	}

	public void scanFolder() {
		
	}
	
}
