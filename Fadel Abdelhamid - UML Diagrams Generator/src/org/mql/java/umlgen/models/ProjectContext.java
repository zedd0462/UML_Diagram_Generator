package org.mql.java.umlgen.models;

import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ProjectContext {

	private ProjectModel currentProject;
	private Map<Class<?>, ClassModel> loadedClasses;
	private Map<Class<?>, InterfaceModel> loadedInterfaces;
	private Map<Class<?>, AnnotationModel> loadedAnnotations;
	private List<RelationModel> relations;
	
	private URLClassLoader classloader;


	public ProjectContext(ProjectModel currentProject) {
		this.currentProject = currentProject;
		loadedClasses = new Hashtable<Class<?>, ClassModel>();
		relations = new Vector<RelationModel>();
		loadedInterfaces = new Hashtable<Class<?>, InterfaceModel>();
		loadedAnnotations = new Hashtable<Class<?>, AnnotationModel>();
	}
	
	public void addClass(ClassModel clazz) {
		loadedClasses.put(clazz.getReflectClass(),clazz);
	}

	public void addInterface(InterfaceModel interf) {
		loadedInterfaces.put(interf.getReflectClass(), interf);
	}

	public void addAnnotation(AnnotationModel annotation) {
		loadedAnnotations.put(annotation.getReflectClass(), annotation);
	}
	
	public void addRelation(RelationModel relation) {
		relations.add(relation);
	}
	
	public ProjectModel getCurrentProject() {
		return currentProject;
	}
	
	public Map<Class<?>, ClassModel> getLoadedClasses() {
		return loadedClasses;
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

	public Map<Class<?>, InterfaceModel> getLoadedInterfaces() {
		return loadedInterfaces;
	}

	public Map<Class<?>, AnnotationModel> getLoadedAnnotations() {
		return loadedAnnotations;
	}
	
	public boolean isLoaded(Class<?> clazz) {
		return loadedClasses.containsKey(clazz);
	}
	
	public ClassModel getLoadedClassModel(Class<?> clazz) {
		return loadedClasses.get(clazz);
	}

	public InterfaceModel getLoadedInterfaceModel(Class<?> clazz) {
		return loadedInterfaces.get(clazz);
	}

	public AnnotationModel getLoadedAnnotationModel(Class<?> clazz) {
		return loadedAnnotations.get(clazz);
	}
	

}
