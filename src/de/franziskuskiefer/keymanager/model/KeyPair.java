package de.franziskuskiefer.keymanager.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyPair extends AbstractModelObject{

	@XmlElement
	private String website = "";
	private String mail = "";

	public KeyPair() {
	}

	public KeyPair(String hp, String m) {
		website = hp;
		mail = m;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebiste(String hp) {
		String oldValue = website;
		website = hp;
		firePropertyChange("website", oldValue, website);
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String m) {
		String oldValue = mail;
		mail = m;
		firePropertyChange("mail", oldValue, mail);
	}

}
