package de.franziskuskiefer.keymanager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PasswordDialogue extends Dialog {

	private String result;
	protected Shell shell;
	private Composite composite;
	private Text text;
	private Button btnOk;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PasswordDialogue(Shell parent) {
		super(parent);
		setText("Keystore Password");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 100);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		Monitor primary = shell.getDisplay().getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shell.getBounds();
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    shell.setLocation(x, y);
		
		composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		text = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text.addListener(SWT.DefaultSelection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				result = text.getText();
				shell.dispose();
			}
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = text.getText();
				shell.dispose();
			}
		});
		btnOk.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		btnOk.setText("OK");

	}

}
