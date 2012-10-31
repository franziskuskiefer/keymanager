package de.franziskuskiefer.keymanager.model;

import static de.franziskuskiefer.keymanager.util.PropertyHelper.prop;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.franziskuskiefer.keymanager.backend.KeyManagerBackend;
import de.franziskuskiefer.keymanager.util.Constants;

public class Model implements Constants {

	private KeyPairs keys;
	private String config;
	private JAXBContext context;
	private KeyManagerBackend keyManager;

	public Model() {
		// read config
		config = prop.getProperty(CONFIG_FILE);
		if (config != null){
			loadConfig();
		} else {
			setKeys(new KeyPairs());
		}
		
		keyManager = new KeyManagerBackend();
	}

	private void loadConfig() {
		try {
			context = JAXBContext.newInstance(KeyPairs.class);
			Unmarshaller um = context.createUnmarshaller();
			setKeys((KeyPairs) um.unmarshal(new FileReader(config)));
		} catch (FileNotFoundException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveKeys(){
		try {
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(getKeys(), System.out);

			Writer w = null;
			try {
				w = new FileWriter(config);
				m.marshal(getKeys(), w);
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

	public void rescanKeystore() {
		System.out.println("Not implemented yet...");
		throw new RuntimeException("Not implemented yet...");
	}

	public void importKeystore(String keystoreFile, String pwd) {
		// check if config exists --- otherwise throw an error (can't work without it)
		String keyStoreFileName = (new File(keystoreFile)).getName();
		String keyStorePath = (new File(keystoreFile)).getParent();
		String configFileName = "."+keyStoreFileName.substring(0, keyStoreFileName.lastIndexOf("."))+".xml";
		String absolutConfigFileName = keyStorePath+File.separator+configFileName;
		if (absolutConfigFileName != null && (new File(absolutConfigFileName)).exists()){
			// store config property
			prop.setProperty(CONFIG_FILE, absolutConfigFileName);
			config = absolutConfigFileName;
			loadConfig();
		} else {
			throw new RuntimeException("Sorry, no config for "+keystoreFile+". Cant't work without it!");
		}
	}
	
	public KeyPairs getKeys() {
		return keys;
	}

	public void setKeys(KeyPairs k) {
		this.keys = k;
	}

	public void addKey(String website, String email) {
		keyManager.generateKey("MeineID", email);
	}

}
