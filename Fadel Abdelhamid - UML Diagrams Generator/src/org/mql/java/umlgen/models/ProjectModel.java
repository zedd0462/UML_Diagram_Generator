package org.mql.java.umlgen.models;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.exceptions.NotValidProjectException;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement(value="project")
public class ProjectModel implements UMLModelEntity{
	
	private String name;
	private String path;
	private List<PackageModel> packages;
	private ProjectContext projectContext;
	private DefaultPackageModel defaultPackage;
	private URLClassLoader classLoader;

	
	public ProjectModel(String projectPath, String projectName) throws NotValidProjectException{
		this(projectPath);
		this.name = projectName;
	}

	public ProjectModel(String projectPath) throws NotValidProjectException {
		this.path = projectPath;
		projectContext = new ProjectContext(this);
		packages = new Vector<PackageModel>();
		
		defaultPackage = new DefaultPackageModel(projectContext);
		if(!defaultPackage.isEmpty()) {
			packages.add(defaultPackage);
		}
		File file = new File(path);
		if(file.isDirectory()) {
			if(file.getName() == "bin") {
				this.name = file.getParentFile().getName();
			}
			try {
				this.classLoader = new URLClassLoader(new URL[] {file.toURI().toURL()}, null);
				this.projectContext.setClassloader(classLoader);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}else{
			throw new NotValidProjectException();
		}
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				PackageModel pkg = new PackageModel(projectContext, f.getAbsolutePath());
				packages.add(pkg);
			} 
		}
		resolveRelations();
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	public void resolveRelations() {
		//TODO: interfaces and annotations;
		Collection<ClassModel> classesCollection = projectContext.getLoadedClasses().values();
		for (ClassModel classModel : classesCollection) {
			classModel.resolveRelations();
		}
	}
	
	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	@SimpleElement(value="path", order=2)
	public String getPath() {
		return path;
	}
	
	@ComplexElement(value="packages", order=3)
	public List<PackageModel> getPackages() {
		return packages;
	}

}
