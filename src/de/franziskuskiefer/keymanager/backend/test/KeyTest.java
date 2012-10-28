package de.franziskuskiefer.keymanager.backend.test;

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
		manager.openKeyStore("test/keys/keystore.ks");
		manager.generateKey("My Name", "me@mail.de");
	}

}
