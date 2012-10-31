package de.franziskuskiefer.keymanager;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.franziskuskiefer.keymanager.model.Model;

public class ModelContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getElements(Object arg0) {
		return ((Model)arg0).getKeys().getKeyPairs().toArray();
	}


}
