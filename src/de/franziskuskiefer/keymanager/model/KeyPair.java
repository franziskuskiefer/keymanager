package de.franziskuskiefer.keymanager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyPair extends AbstractModelObject{

	@XmlElement
	private String website = "";
	private String keyID = "";

	public KeyPair() {
	}

	public KeyPair(String hp, String id) {
		website = hp;
		keyID = id;
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

}
