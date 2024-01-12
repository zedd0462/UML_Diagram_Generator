package org.mql.java.umlgen.models;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="package")
public class PackageModel implements UMLModelEntity{
	
	protected String packageShortName;
	protected String packageFullName;
	protected List<ClassModel> classes;
	protected List<InterfaceModel> interfaces;
	protected List<PackageModel> subPackages;
	protected List<AnnotationModel> annotatons;
	protected ProjectContext projectContext;
	protected String path;

	protected PackageModel() {
		classes = new Vector<ClassModel>();
		interfaces = new Vector<InterfaceModel>();
		subPackages = new Vector<PackageModel>();
		annotatons = new Vector<AnnotationModel>();
	}

	public PackageModel(ProjectContext projectContext, String pathToPackageDirectory) {
		this();
		this.projectContext = projectContext;
		this.path = pathToPackageDirectory;
		String projectPath = projectContext.getCurrentProject().getPath();
		String relativePath = pathToPackageDirectory.substring(projectPath.length() + 1);
		packageFullName = relativePath.replace(File.separator, ".");
		packageShortName = packageFullName.substring(packageFullName.lastIndexOf(".") + 1);
		packageExplorer();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	protected void packageExplorer() {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				PackageModel subPackage = new PackageModel(projectContext, f.getAbsolutePath());
				subPackages.add(subPackage);
			} else {
				String fileName = f.getName();
				if (fileName.endsWith(".class")) {
					String className = fileName.substring(0, fileName.length() - 6);
					try {
						Class<?> clazz = projectContext.getClassloader().loadClass(packageFullName + "."  + className);
						if (clazz.isAnnotation()) {
							AnnotationModel model = new AnnotationModel(clazz);
							annotatons.add(model);
							projectContext.addAnnotation(model);
						} else if (clazz.isInterface()) {
							InterfaceModel model = new InterfaceModel(projectContext, clazz);
							interfaces.add(model);
							projectContext.addInterface(model);
						} else {
							ClassModel model = new ClassModel(projectContext, clazz);
							classes.add(model);
							projectContext.addClass(model);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public String getPackageShortName() {
		return packageShortName;
	}

	@SimpleElement(value="name", order=1)
	public String getName() {
		return packageFullName;
	}

	@ComplexElement(value="classes", order=3)
	public List<ClassModel> getClasses() {
		return classes;
	}
	
	@ComplexElement(value="interfaces", order=4)
	public List<InterfaceModel> getInterfaces() {
		return interfaces;
	}

	@ComplexElement(value="packages", order=6)
	public List<PackageModel> getSubPackages() {
		return subPackages;
	}

	@ComplexElement(value="annotations", order=5)
	public List<AnnotationModel> getAnnotatons() {
		return annotatons;
	}

	public ProjectContext getProjectContext() {
		return projectContext;
	}

	@SimpleElement(value="path", order=2)
	public String getPath() {
		return path;
	}
	
	

}
