package de.franziskuskiefer.pgp.keymanager.model;

import java.io.FileWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class test {

	public static void main(String[] args) throws Exception {
		KeyPairs keys = new KeyPairs();
		JAXBContext context = JAXBContext.newInstance(KeyPairs.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    m.marshal(keys, System.out);

	    Writer w = null;
	    try {
	      w = new FileWriter("./testkeys");
	      m.marshal(keys, w);
	    } finally {
	      try {
	        w.close();
	      } catch (Exception e) {
	      }
	    }
	}
	
}
