package de.franziskuskiefer.keymanager.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class KeyManagerBackend {

	private static final ProtectionParameter EMPTY_KEYSTORE_ENTRY_PASSWORD = new KeyStore.PasswordProtection(new char[0]);
	private KeyStore ks;
	private File f;
	
	public KeyManagerBackend() throws Exception {
		Security.addProvider(new BouncyCastleProvider());
//		createKeyStore(new char[0]);
//		KeyPair k = createKeyPair();
//		X509Certificate cert = createCert("ABC def", k, "me@blub.de");
//		addKeyToKeyStore(k, "me@blub.de", cert);
//		saveKeyStore("test/keys/keystore.ks", new char[0]);
		
//		loadKeyStore(new char[0], "test/keys/keystore.ks");
//		X509Certificate cert = createCert("ABC def", k, "me3@blub.de");
//		addKeyToKeyStore(k, "me3@blub.de", cert);
//		saveKeyStore("test/keys/keystore.ks", new char[0]);
	}
	
	public void openKeyStore(String file){
		f = new File(file);
		if (f.exists()){
			loadKeyStore(new char[0], f);
		} else {
			createKeyStore(new char[0]);
		}
	}
	
	public void generateKey(String id, String mail){
		KeyPair k = createKeyPair();
		X509Certificate cert = createCert(id, k, mail);
		addKeyToKeyStore(k, mail, cert);
		saveKeyStore(f, new char[0]);
	}
	
	private void saveKeyStore(File file, char[] pwd) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			ks.store(fos, pwd);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
			}
		}
	}

	protected void createKeyStore(char[] password) {
		try {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(null, password);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadKeyStore(char[] password, File file) {
		try {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(new FileInputStream(file), password);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addKeyToKeyStore(KeyPair k, String id, X509Certificate cert) {
		KeyStore.PrivateKeyEntry skEntry = new KeyStore.PrivateKeyEntry(k.getPrivate(), new Certificate[]{cert});
		try {
			ks.setEntry(id, skEntry, EMPTY_KEYSTORE_ENTRY_PASSWORD);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private KeyPair createKeyPair() {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			return kp;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private X509Certificate createCert(String id, KeyPair kp, String mail) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

		builder.addRDN(BCStyle.CN, id);
        builder.addRDN(BCStyle.C, "NN");
        builder.addRDN(BCStyle.O, "Private");
        builder.addRDN(BCStyle.L, "NN");
        builder.addRDN(BCStyle.ST, "NN");
        builder.addRDN(BCStyle.EmailAddress, mail);
		
		PublicKey pk = kp.getPublic();
		X500Name subject;
		Date notAfter = new Date(System.currentTimeMillis()+System.currentTimeMillis());
		Date notBefore = new Date(System.currentTimeMillis());
		BigInteger serial = BigInteger.ONE;
		X500Name name = subject = builder.build();
		X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(name, serial, notBefore, notAfter, subject, pk);

	    ContentSigner sigGen;
		try {
			sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider("BC").build(kp.getPrivate());
			X509CertificateHolder holder = certGen.build(sigGen);
			return new JcaX509CertificateConverter().setProvider("BC").getCertificate(holder);
		} catch (OperatorCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
