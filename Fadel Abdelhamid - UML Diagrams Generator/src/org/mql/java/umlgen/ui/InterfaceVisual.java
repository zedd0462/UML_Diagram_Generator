package org.mql.java.umlgen.ui;

import org.mql.java.umlgen.models.InterfaceModel;
import org.mql.java.umlgen.utils.StringUtils;

public class InterfaceVisual extends EntityVisual{
	
	private static final long serialVersionUID = 1L;
	
	private InterfaceModel interfaceModel;

	public InterfaceVisual(InterfaceModel interfaceModel) {
		this.interfaceModel = interfaceModel;
		init();
	}

	@Override
	protected void initStrings() {
		initFields(interfaceModel.getFields());
		initMethods(interfaceModel.getMethods());
	}

	@Override
	protected void initName() {
		this.entityName = "<<interface>>" + StringUtils.getClassShortName(interfaceModel.getName());
	}

	@Override
	protected boolean hasFields() {
		return true;
	}

	@Override
	protected boolean hasConstructors() {
		return false;
	}

	@Override
	protected boolean hasMethods() {
		return true;
	}
	
	public InterfaceModel getInterfaceModel() {
		return interfaceModel;
	}

}
