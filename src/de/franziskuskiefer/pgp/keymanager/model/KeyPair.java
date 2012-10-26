package de.franziskuskiefer.pgp.keymanager.model;

public class KeyPair extends AbstractModelObject{

	private String website = "google.com";
	
	private String keyID = "";
	private String publicKey = "";

	public KeyPair() {
	}

	public KeyPair(String hp, String id, String pub) {
		website = hp;
		keyID = id;
		publicKey = pub;
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
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(String pk) {
		String oldValue = publicKey;
		publicKey = pk;
		firePropertyChange("publicKey", oldValue, publicKey);
	}
}
