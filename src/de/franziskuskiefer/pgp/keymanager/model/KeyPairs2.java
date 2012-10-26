package de.franziskuskiefer.pgp.keymanager.model;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class KeyPairs2 extends Shell {

	private KeyPairs2Controller m_controller;
	private Text keyIDText;
	private Text publicKeyText;
	private Text webisteText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = new Display();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					Display display = Display.getDefault();
					KeyPairs2 shell = new KeyPairs2(display, SWT.SHELL_TRIM);
					shell.open();
					shell.layout();
					while (!shell.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the shell.
	 * @param display
	 * @param style
	 */
	public KeyPairs2(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);
		setLayout(new GridLayout(2, false));

		new Label(this, SWT.NONE).setText("KeyID:");

		keyIDText = new Text(this, SWT.BORDER | SWT.SINGLE);
		keyIDText
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("PublicKey:");

		publicKeyText = new Text(this, SWT.BORDER | SWT.SINGLE);
		publicKeyText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Webiste:");

		webisteText = new Text(this, SWT.BORDER | SWT.SINGLE);
		webisteText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		m_controller = new KeyPairs2Controller(this);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getKeyIDText() {
		return keyIDText;
	}

	public Text getPublicKeyText() {
		return publicKeyText;
	}

	public Text getWebisteText() {
		return webisteText;
	}

}
