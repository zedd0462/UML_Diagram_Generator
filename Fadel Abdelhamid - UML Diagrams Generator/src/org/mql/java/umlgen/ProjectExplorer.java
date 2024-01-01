package org.mql.java.umlgen;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.exceptions.NotValidProjectException;

/**
 * Explores a Java Project and gets information about
 * all of its Packages, Classes, Interfaces...etc.
 */
public class ProjectExplorer {
	
	private File projectRoot;
	private List<Class<?>> loadedClasses;
	private URLClassLoader classloader;

	public ProjectExplorer(String projectPath) throws NotValidProjectException {
		try {
			projectRoot = new File(projectPath);
			if(!(projectRoot.exists() || !projectRoot.isDirectory())) {
				throw new NotValidProjectException();
			}
			this.loadedClasses = new Vector<Class<?>>();
			this.classloader = new URLClassLoader(new URL[] {projectRoot.toURI().toURL()}, null);
			explore();
		} catch (Exception e) {
			throw new NotValidProjectException();
		}
	}
	
	/**
	 * Explores all subfiles and subdirectories of the project for .class files
	 * @throws ClassNotFoundException
	 */
	private void explore() throws ClassNotFoundException{
		explore(this.projectRoot);
	}
	
	/**
	 * Explores all subfiles and subdirectories for .class files
	 * @param file The root of the Java project that contains .class files
	 * @throws ClassNotFoundException
	 */
	private void explore(File file) throws ClassNotFoundException{
		File[] content = file.listFiles();
		for (File subfile : content) {
			if(subfile.isDirectory()) {
				explore(subfile);
			}else {
				if(subfile.getName().endsWith(".class")) {
					loadClass(getQNameFromPath(subfile.getAbsolutePath()));
				}
			}
		}
	}
	
	private void loadClass(String QName) throws ClassNotFoundException {
		loadedClasses.add(classloader.loadClass(QName));
	}
	
	/**
	 * Returns the class qualified name from it's absolute .class file
	 * @param path Path of the .class file
	 * @return Qualified name of the class
	 */
	public String getQNameFromPath(String path) {
		int indexToRemove = path.lastIndexOf(".class");
		path = path.substring(0, indexToRemove);
		path = path.replace(projectRoot.getAbsolutePath(), "");
		path = path.replace('\\', '.');
		if(path.startsWith(".")) {
			path = path.replaceFirst(".", "");
		}
		return path;
	}
	
	public List<Class<?>> getLoadedClasses() {
		return loadedClasses;
	}
	
	public List<Package> getLoadedPackages() {
		List<Package> loadedPackages = new Vector<Package>();
		Collections.addAll(loadedPackages, classloader.getDefinedPackages());
		return loadedPackages;
	}
	
	public URLClassLoader getClassloader() {
		return classloader;
	}
	

}
