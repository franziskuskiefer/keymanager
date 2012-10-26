package de.franziskuskiefer.pgp.keymanager.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyPairs {

	@XmlElement
	private List<KeyPair> keyPairs = new LinkedList<KeyPair>();
	
	public List<KeyPair> getKeyPairs() {
		return keyPairs;
	}

	public KeyPairs() {
		// XXX: Testing: fill list
		/*
		keyPairs.add(new KeyPair("google.com", "0xABCDEF", "bla", "blub"));
		keyPairs.add(new KeyPair("lakhsd.com", "0x7654JH", "swergser", "asdf"));
		keyPairs.add(new KeyPair("sdf.com", "0x87654EF", "", ""));
		keyPairs.add(new KeyPair("zrhb.com", "0x123EF", "", ""));
		keyPairs.add(new KeyPair("poektg.com", "0xABGWEEF", "", ""));
		keyPairs.add(new KeyPair("ijrv.com", "0xF3FAA202", "aergaer", "agre"));
		*/
	}
	
	
	
}
