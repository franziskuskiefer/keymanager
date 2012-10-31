package de.franziskuskiefer.keymanager.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KeyPair {

	@XmlElement
	private String website = "";
	private String mail = "";
	
	/**
	 * Listener stuff for TableViewer
	 */
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	
	
	public KeyPair() {
		
	}

	public KeyPair(String hp, String m) {
		setMail(m);
		setWebiste(hp);
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebiste(String hp) {
		propertyChangeSupport.firePropertyChange("website", this.website, this.website = hp);
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String m) {
		propertyChangeSupport.firePropertyChange("mail", this.mail, this.mail = m);
	}

}
