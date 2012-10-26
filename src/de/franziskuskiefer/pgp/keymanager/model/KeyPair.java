package de.franziskuskiefer.pgp.keymanager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyPair extends AbstractModelObject{

	@XmlElement
	private String website = "";
	private String keyID = "";
	private String publicKeyFile = "";
	private String privateKeyFile = "";

	public KeyPair() {
	}

	public KeyPair(String hp, String id, String pub, String priv) {
		website = hp;
		keyID = id;
		publicKeyFile = pub;
		privateKeyFile = priv;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebiste(String hp) {
		String oldValue = website;
		website = hp;
		firePropertyChange("website", oldValue, website);
	}
	
	public String getKeyID() {
		return keyID;
	}
	
	public void setKeyID(String id) {
		String oldValue = keyID;
		keyID = id;
		firePropertyChange("keyID", oldValue, keyID);
	}

	public String getPrivateKeyFile() {
		return privateKeyFile;
	}

	public void setPrivateKeyFile(String priv) {
		String oldValue = privateKeyFile;
		privateKeyFile = priv;
		firePropertyChange("privateKeyFile", oldValue, privateKeyFile);
	}

	public String getPublicKeyFile() {
		return publicKeyFile;
	}

	public void setPublicKeyFile(String pub) {
		String oldValue = publicKeyFile;
		publicKeyFile = pub;
		firePropertyChange("publicKeyFile", oldValue, publicKeyFile);
	}
}
