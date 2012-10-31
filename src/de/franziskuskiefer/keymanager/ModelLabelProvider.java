package de.franziskuskiefer.keymanager;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.franziskuskiefer.keymanager.model.KeyPair;


public class ModelLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public String getText(Object element) {
		return super.getText(element);
	}
	
	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object keyPair, int columnNum) {
		switch (columnNum) {
			case 0:
				return ((KeyPair)keyPair).getWebsite();
			case 1:
				return ((KeyPair)keyPair).getMail();
			default:
				return "ERROR";
		}
	}



}
