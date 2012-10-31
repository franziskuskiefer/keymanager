package de.franziskuskiefer.keymanager.model;

import static de.franziskuskiefer.keymanager.util.PropertyHelper.prop;

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
	private String keystoreFile;

	public Model() {
		// initialise key manager
		keyManager = new KeyManagerBackend();
		
		// read config
		config = prop.getProperty(CONFIG_FILE);
		if (config != null){
			loadConfig();
		} else {
			setKeys(new KeyPairs());
			// TODO: what should I do here? Wizard?
			String defaultConfigFile = "/home/franziskus/workspace/KeyManager/test/keys/.newKeystore.xml";
			String defaultKeyStoreFile = "/home/franziskus/workspace/KeyManager/test/keys/newKeystore.ks";
			prop.setProperty(CONFIG_FILE, defaultConfigFile);
			config = defaultConfigFile;
			keystoreFile = defaultKeyStoreFile;
		}
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
			context = JAXBContext.newInstance(KeyPairs.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

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
		
		// get the file names clear at first
//		File file = new File(keystoreFile);
//		if (file.exists()){
//			String keyStoreFileName = file.getName();
//			String keyStorePath = (new File(keystoreFile)).getParent();
//			String configFileName = "."+keyStoreFileName.substring(0, keyStoreFileName.lastIndexOf("."))+".xml";
//			String absolutConfigFileName = keyStorePath+File.separator+configFileName;
			
		// check if config exists --- otherwise throw an error (can't work without it)
		if (config != null && (new File(config)).exists()){
			// store config property
			loadConfig();
			this.keystoreFile = keystoreFile;		
		} else if (keystoreFile != null && (new File(keystoreFile)).exists()) {
			throw new RuntimeException("Sorry, no config for "+keystoreFile+". Cant't work without it!");
		}
//		}
		// load keystore
		keyManager.openKeyStore(keystoreFile, pwd);
	}
	
	public KeyPairs getKeys() {
		return keys;
	}

	public void setKeys(KeyPairs k) {
		this.keys = k;
	}

	public void addKey(String website, String email) {
		keyManager.generateKey(website+":"+email, email);
		getKeys().getKeyPairs().add(new KeyPair(website, email));
		saveKeys();
	}

	public void importKeystore(String password) {
		importKeystore(keystoreFile, password);
	}

	public boolean hasKeyStore() {
		if (config != null)
			return true;
		return false;
	}

}
