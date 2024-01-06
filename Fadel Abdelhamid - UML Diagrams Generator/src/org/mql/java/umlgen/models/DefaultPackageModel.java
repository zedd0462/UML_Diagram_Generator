package org.mql.java.umlgen.models;

import java.io.File;

import org.mql.java.umlgen.annotations.ComplexElement;

@ComplexElement(value="package")
public class DefaultPackageModel extends PackageModel {
	
	private boolean isEmpty;

	public DefaultPackageModel(ProjectContext projectContext) {
		super();
		this.isEmpty = true;
		this.projectContext = projectContext;
		this.packageFullName = "defaultPackage";
		this.packageShortName = "defaultPackage";
		this.path = projectContext.getCurrentProject().getPath();
		this.packageExplorer();
	}
	
	@Override
	protected void packageExplorer() {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				//packages that exist here will be explored later
			} else {
				String fileName = f.getName();
				if (fileName.endsWith(".class")) {
					isEmpty = false;
					String className = fileName.substring(0, fileName.length() - 6);
					try {
						Class<?> clazz = Class.forName(packageFullName + "." + className);
						if (clazz.isAnnotation()) {
							annotatons.add(new AnnotationModel(clazz));
						} else if (clazz.isInterface()) {
							interfaces.add(new InterfaceModel(clazz));
						} else {
							classes.add(new ClassModel(projectContext, clazz));
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

}
