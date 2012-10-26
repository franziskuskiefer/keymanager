package de.franziskuskiefer.pgp.keymanager.model;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.UpdateValueStrategy;


public class KeyPairs2Controller {
	private KeyPairs2 m_keyPairs2;
	private DataBindingContext m_bindingContext;
	private de.franziskuskiefer.pgp.keymanager.model.KeyPair keyPair = new de.franziskuskiefer.pgp.keymanager.model.KeyPair();

	public KeyPairs2Controller(KeyPairs2 keyPairs2, de.franziskuskiefer.pgp.keymanager.model.KeyPair newKeyPair) {
		m_keyPairs2 = keyPairs2;
		setKeyPair(newKeyPair);
	}

	public KeyPairs2Controller(KeyPairs2 keyPairs2) {
		m_keyPairs2 = keyPairs2;
		if (keyPair != null) {
			m_bindingContext = initDataBindings();
		}
	}

	private DataBindingContext initDataBindings() {
		IObservableValue keyIDObserveWidget = SWTObservables.observeText(m_keyPairs2.getKeyIDText(), SWT.Modify);
		IObservableValue keyIDObserveValue = BeansObservables.observeValue(keyPair, "keyID");
		IObservableValue publicKeyObserveWidget = SWTObservables.observeText(m_keyPairs2.getPublicKeyText(), SWT.Modify);
		IObservableValue publicKeyObserveValue = BeansObservables.observeValue(keyPair, "publicKey");
		IObservableValue webisteObserveWidget = SWTObservables.observeText(m_keyPairs2.getWebisteText(), SWT.Modify);
		IObservableValue webisteObserveValue = BeansObservables.observeValue(keyPair, "webiste");
		//
		DataBindingContext bindingContext = new DataBindingContext();
		//
		bindingContext.bindValue(keyIDObserveWidget, keyIDObserveValue, null, null);
		bindingContext.bindValue(publicKeyObserveWidget, publicKeyObserveValue, null, null);
		bindingContext.bindValue(webisteObserveWidget, webisteObserveValue, null, null);
		//
		return bindingContext;
	}

	public de.franziskuskiefer.pgp.keymanager.model.KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(de.franziskuskiefer.pgp.keymanager.model.KeyPair newKeyPair) {
		setKeyPair(newKeyPair, true);
	}

	public void setKeyPair(de.franziskuskiefer.pgp.keymanager.model.KeyPair newKeyPair, boolean update) {
		keyPair = newKeyPair;
		if (update) {
			if (m_bindingContext != null) {
				m_bindingContext.dispose();
				m_bindingContext = null;
			}
			if (keyPair != null) {
				m_bindingContext = initDataBindings();
			}
		}
	}
}