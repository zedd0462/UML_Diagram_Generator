package org.mql.java.umlgen;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.exceptions.NotValidProjectException;

/**
 * Explores a Java Project and gets information about all of its Packages, Classes, Interfaces...etc.
 */
public class ProjectExplorer {
	
	private File projectRoot;
	private List<Class<?>> loadedClasses;
	private URLClassLoader classloader;

	public ProjectExplorer(String projectPath) throws Exception {
		projectRoot = new File(projectPath);
		if(!(projectRoot.exists() || !projectRoot.isDirectory())) {
			throw new NotValidProjectException();
		}
		this.loadedClasses = new Vector<Class<?>>();
		this.classloader = new URLClassLoader(new URL[] {projectRoot.toURI().toURL()});
		explore();
	}
	
	private void explore() {
		explore(this.projectRoot);
	}
	
	/**
	 * Explores all subfiles and subdirectories for .class files
	 * @param file The root of the Java project that contains .class files
	 */
	private void explore(File file) {
		File[] content = file.listFiles();
		for (File subfile : content) {
			if(subfile.isDirectory()) {
				explore(subfile);
			}else {
				if(subfile.getName().endsWith(".class")) {
					try {
						loadClass(getQNameFromPath(subfile.getAbsolutePath()));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void loadClass(String QName) throws ClassNotFoundException {
		loadedClasses.add(classloader.loadClass(QName));
	}
	
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
	
	public URLClassLoader getClassloader() {
		return classloader;
	}
	

}
