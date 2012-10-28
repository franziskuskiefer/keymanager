package de.franziskuskiefer.keymanager;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.franziskuskiefer.keymanager.model.KeyPair;
import de.franziskuskiefer.keymanager.model.KeyPairs;
import de.franziskuskiefer.keymanager.model.Model;

public class KeyManager {
	private Binding website;
	private DataBindingContext m_bindingContext;

	protected Shell shell;
	private String chosenDirectory;
	private Table table;
	private KeyPairs keys;
	private TableViewer tableViewer;
	private TableColumn tblclmnWebsite;
	private Model model;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(400, 597);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell);
				chosenDirectory = dialog.open();
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
				System.out.println("Close me...");
			}
		});
		mntmExit.setText("Exit");
		
		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");
		
		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);
		
		MenuItem mntmRescanKeys = new MenuItem(menu_2, SWT.NONE);
		mntmRescanKeys.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				model.scanFolder();
			}
		});
		mntmRescanKeys.setText("Rescan Keys");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tblclmnWebsite = tableViewerColumn.getColumn();
		tblclmnWebsite.setWidth(276);
		tblclmnWebsite.setText("Website");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnKeyID = tableViewerColumn_2.getColumn();
		tblclmnKeyID.setWidth(100);
		tblclmnKeyID.setText("KeyID");
		
		m_bindingContext = initDataBindings();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableSetContentProvider setContentProvider = new ObservableSetContentProvider();
		tableViewer.setContentProvider(setContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
					setContentProvider.getKnownElements(), KeyPair.class,
					new String[] { "website", "keyID"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableSet writableSet = new WritableSet(keys.getKeyPairs(), KeyPair.class);
		tableViewer.setInput(writableSet);
		//
		return bindingContext;
	}
}
