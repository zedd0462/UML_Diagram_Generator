package org.mql.java.umlgen.models;

import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ProjectContext {

	private ProjectModel currentProject;
	private Map<String, ClassModel> loadedClasses;
	private Map<String, InterfaceModel> loadedInterfaces;
	private Map<String, AnnotationModel> loadedAnnotations;
	private Map<String, PackageModel> loadedPackages;
	private List<RelationModel> relations;
	
	private URLClassLoader classloader;

	public ProjectContext(ProjectModel currentProject) {
		this.currentProject = currentProject;
		initLists();
	}
	
	public void initLists() {
		loadedClasses = new Hashtable<String, ClassModel>();
		loadedInterfaces = new Hashtable<String, InterfaceModel>();
		loadedAnnotations = new Hashtable<String, AnnotationModel>();
		loadedPackages = new Hashtable<String, PackageModel>();
		relations = new Vector<RelationModel>();
	}
	
	public void addPackage(PackageModel pkg) {
		loadedPackages.put(pkg.getName(), pkg);
	}
	
	public void addClass(ClassModel clazz) {
		loadedClasses.put(clazz.getName(),clazz);
	}

	public void addInterface(InterfaceModel interf) {
		loadedInterfaces.put(interf.getName(), interf);
	}

	public void addAnnotation(AnnotationModel annotation) {
		loadedAnnotations.put(annotation.getName(), annotation);
	}
	
	public void addRelation(RelationModel relation) {
		relations.add(relation);
	}
	
	public ProjectModel getCurrentProject() {
		return currentProject;
	}
	
	public List<RelationModel> getRelations() {
		return relations;
	}
	
	public void setClassloader(URLClassLoader classloader) {
		this.classloader = classloader;
	}
	
	public URLClassLoader getClassloader() {
		return classloader;
	}

	public Map<String, ClassModel> getLoadedClasses() {
		return loadedClasses;
	}
	
	public Map<String, InterfaceModel> getLoadedInterfaces() {
		return loadedInterfaces;
	}

	public Map<String, AnnotationModel> getLoadedAnnotations() {
		return loadedAnnotations;
	}
	
	/**
	 * Returns boolean value if the class has been loaded in the project
	 * that this context belongs to.
	 * @param clazz Reflect class object
	 * @return If the class is loaded.
	 */
	public boolean isLoaded(String classname) {
		return loadedClasses.containsKey(classname) || loadedInterfaces.containsKey(classname) || loadedAnnotations.containsKey(classname);
	}
	
	/**
	 * Returns ClassModel representation from a reflect class if it's loaded in the project.
	 * @param clazz Reflect class object
	 * @return The Corresponding ClassModel
	 */
	public ClassModel getLoadedClassModel(String classname) {
		return loadedClasses.get(classname);
	}

	public InterfaceModel getLoadedInterfaceModel(String interfaceName) {
		return loadedInterfaces.get(interfaceName);
	}

	public AnnotationModel getLoadedAnnotationModel(String annotationName) {
		return loadedAnnotations.get(annotationName);
	}
	
	public Entity getLoadedRelationEntity(String classname) {
		if (loadedAnnotations.containsKey(classname)) {
			return getLoadedAnnotationModel(classname);
		}
		if (loadedInterfaces.containsKey(classname)) {
			return getLoadedInterfaceModel(classname);
		}
		return getLoadedClassModel(classname);
	}
	

}
