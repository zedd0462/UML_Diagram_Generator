package org.mql.java.umlgen.models;

import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;

public class ProjectContext {

	private ProjectModel currentProject;
	private List<ClassModel> loadedClasses;
	private List<InterfaceModel> loadedInterfaces;
	private List<AnnotationModel> loadedAnnotations;
	private List<RelationModel> relations;
	
	private URLClassLoader classloader;


	public ProjectContext(ProjectModel currentProject) {
		this.currentProject = currentProject;
		loadedClasses = new Vector<ClassModel>();
		relations = new Vector<RelationModel>();
		loadedInterfaces = new Vector<InterfaceModel>();
		loadedAnnotations = new Vector<AnnotationModel>();
	}
	
	public void addClass(ClassModel clazz) {
		loadedClasses.add(clazz);
	}

	public void addInterface(InterfaceModel interf) {
		loadedInterfaces.add(interf);
	}

	public void addAnnotation(AnnotationModel annotation) {
		loadedAnnotations.add(annotation);
	}
	
	public void addRelation(RelationModel relation) {
		relations.add(relation);
	}
	
	public ProjectModel getCurrentProject() {
		return currentProject;
	}
	
	public List<ClassModel> getLoadedClasses() {
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

	public List<InterfaceModel> getLoadedInterfaces() {
		return loadedInterfaces;
	}

	public List<AnnotationModel> getLoadedAnnotations() {
		return loadedAnnotations;
	}
	
	
	

}
