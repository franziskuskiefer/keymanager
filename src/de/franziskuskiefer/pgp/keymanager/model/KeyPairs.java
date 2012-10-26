package de.franziskuskiefer.pgp.keymanager.model;

import java.util.LinkedList;
import java.util.List;

public class KeyPairs {

	private List<KeyPair> keyPairs = new LinkedList<KeyPair>();
	
	public List<KeyPair> getKeyPairs() {
		return keyPairs;
	}

	public KeyPairs() {
		// XXX: Testing: fill list
		keyPairs.add(new KeyPair("google.com", "0xABCDEF", "0xblasdblahdnladhiashihd"));
		keyPairs.add(new KeyPair("lakhsd.com", "0x7654JH", "0x1234dblahdnladhiashihd"));
		keyPairs.add(new KeyPair("sdf.com", "0x87654EF", "0xOIHNJEFlahdnladhiashihd"));
		keyPairs.add(new KeyPair("zrhb.com", "0x123EF", "0x6GH762JHhdnladhiashihd"));
		keyPairs.add(new KeyPair("poektg.com", "0xABGWEEF", "0xOIHEsdblahdnladhiashihd"));
		keyPairs.add(new KeyPair("ijrv.com", "0xAHEHEF", "0x8734rKNblahdnladhiashihd"));
	}
	
	
	
}
