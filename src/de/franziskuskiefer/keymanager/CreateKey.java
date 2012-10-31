package de.franziskuskiefer.keymanager;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CreateKey extends Dialog {

	protected String[] result = new String[2];
	protected Shell shlSignUp;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CreateKey(Shell parent) {
		super(parent);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String[] open() {
		createContents();
		shlSignUp.open();
		shlSignUp.layout();
		Display display = getParent().getDisplay();
		while (!shlSignUp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlSignUp = new Shell(getParent(), getStyle());
		shlSignUp.setSize(450, 115);
		shlSignUp.setText("Sign Up");
		shlSignUp.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlSignUp, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		
		Label lblWebsite = new Label(composite, SWT.NONE);
		lblWebsite.setText("Website");
		new Label(composite, SWT.NONE);
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setText("E-Mail");
		new Label(composite, SWT.NONE);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result[0] = text.getText();
				result[1] = text_1.getText();
				shlSignUp.dispose();
			}
		});
		btnOk.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnOk.setText("OK");

	}

}
