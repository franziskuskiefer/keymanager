package de.franziskuskiefer.keymanager;

import java.io.File;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.franziskuskiefer.keymanager.model.KeyPairs;
import de.franziskuskiefer.keymanager.model.Model;

public class KeyManager {

	protected Shell shlKeyManager;
	private Table table;
	private TableViewer tableViewer;
	private TableColumn tblclmnWebsite;
	private Model model;

	private KeyPairs keys;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					KeyManager window = new KeyManager();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KeyManager() {
		// create model
		model = new Model();
		keys = model.getKeys();
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		optionalLogin();
		shlKeyManager.open();
		shlKeyManager.layout();
		while (!shlKeyManager.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void optionalLogin() {
		if(model.hasKeyStore()){
			PasswordDialogue pwdDialog = new PasswordDialogue(shlKeyManager);
			String password = pwdDialog.open();
			model.importKeystore(password);
			tableViewer.refresh();
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKeyManager = new Shell();
		shlKeyManager.setSize(400, 597);
		shlKeyManager.setText("Key Manager");
		shlKeyManager.setLayout(new FillLayout(SWT.HORIZONTAL));
		Monitor primary = shlKeyManager.getDisplay().getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlKeyManager.getBounds();
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    shlKeyManager.setLocation(x, y);
		
		Menu menu = new Menu(shlKeyManager, SWT.BAR);
		shlKeyManager.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlKeyManager);
				String keystoreFile = dialog.open();
				if (keystoreFile != null && (new File(keystoreFile)).exists()){
					PasswordDialogue pwdDialog = new PasswordDialogue(shlKeyManager);
					String password = pwdDialog.open();
					model.importKeystore(keystoreFile, password);
					tableViewer.refresh();
				}
			}
		});
		mntmOpen.setText("Open");
		
		MenuItem mntmPreferences = new MenuItem(menu_1, SWT.NONE);
		mntmPreferences.setText("Preferences");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlKeyManager.dispose();
			}
		});
		mntmExit.setText("Exit");
		
		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");
		
		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);
		
		MenuItem mntmCreateKey = new MenuItem(menu_2, SWT.NONE);
		mntmCreateKey.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateKey pwdDialog = new CreateKey(shlKeyManager);
				String[] keyProperties = pwdDialog.open();
				model.addKey(keyProperties[0], keyProperties[1]);
				tableViewer.refresh();
			}
		});
		mntmCreateKey.setText("Create Key");
		
		MenuItem mntmRescanKeys = new MenuItem(menu_2, SWT.NONE);
		mntmRescanKeys.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				FIXME: model.rescanKeystore();
				tableViewer.refresh();
			}
		});
		mntmRescanKeys.setText("Rescan Keys");
		
		Composite composite = new Composite(shlKeyManager, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn column1 = tableViewerColumn.getColumn();
		column1.setWidth(276);
		column1.setText("Website");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnMail = tableViewerColumn_2.getColumn();
		tblclmnMail.setWidth(100);
		tblclmnMail.setText("Mail");
		
		// for the data binding
		tableViewer.setContentProvider(new ModelContentProvider());
		tableViewer.setLabelProvider(new ModelLabelProvider());
		// Get the content for the viewer, setInput will call getElements in the contentProvider
		tableViewer.setInput(model);
 	}
}
