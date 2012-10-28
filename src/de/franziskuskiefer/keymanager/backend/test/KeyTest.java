package de.franziskuskiefer.keymanager.backend.test;

import java.io.File;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.franziskuskiefer.keymanager.backend.KeyManagerBackend;

public class KeyTest {

	private KeyManagerBackend manager;

	@Before
	public void setUp() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		manager = new KeyManagerBackend();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreating() {
		// delete keystore
		File f = new File("test/keys/keystore.ks");
		if (f.exists())
			f.delete();
		// test
		manager.openKeyStore("test/keys/keystore.ks", "keyStorePassword");
		manager.generateKey("My Name", "me@mail.de");
		manager.generateKey("My Second Name", "another@mail-address.de");
	}
	
	@Test
	public void testCreatingErrors() {
		// delete keystore
		File f = new File("test/keys/keystore.ks");
		if (f.exists())
			f.delete();
		// test
		manager.openKeyStore("test/keys/keystore.ks", "keyStorePassword");
		manager.generateKey("My Name", "me@mail.de");
		try {
			manager.generateKey("My Name", "me@mail.de");
			assert(false);
		} catch (IllegalArgumentException e){
			System.out.println(e.getLocalizedMessage());
			assert(true);
		}
	}

}
