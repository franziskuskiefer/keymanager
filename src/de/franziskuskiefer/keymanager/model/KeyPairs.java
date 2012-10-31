package de.franziskuskiefer.keymanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.PROPERTY)
public class KeyPairs {

	private List<KeyPair> keyPairs = new ArrayList<KeyPair>();
	
	@XmlElement(name="keyPairs")
	public List<KeyPair> getKeyPairs() {
		return keyPairs;
	}
	
	public void setKeyPairs(List<KeyPair> kp) {
		this.keyPairs = kp;
	}

	public KeyPairs() {
		
	}
	
}
