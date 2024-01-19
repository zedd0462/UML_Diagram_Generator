package org.mql.java.umlgen.ui;

import org.mql.java.umlgen.models.ClassModel;
import org.mql.java.umlgen.utils.StringUtils;

public class ClassVisual extends EntityVisual{
	
	private static final long serialVersionUID = 1L;
	private ClassModel classModel;	

	public ClassVisual(ClassModel classModel) {
		this.classModel = classModel;
		init();
	}


	@Override
	protected void initName() {
		this.entityName = StringUtils.getClassShortName(classModel.getName());
	}
	
	@Override
	protected void initStrings() {
		initFields(classModel.getFields());
		initMethods(classModel.getMethods());
		initConstructors(classModel.getConstructors());
	}

	@Override
	protected boolean hasFields() {
		return true;
	}

	@Override
	protected boolean hasConstructors() {
		return true;
	}

	@Override
	protected boolean hasMethods() {
		return true;
	}



	

}
