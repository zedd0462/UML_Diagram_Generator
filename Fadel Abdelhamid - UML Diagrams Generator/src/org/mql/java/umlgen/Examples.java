package org.mql.java.umlgen;

import java.util.List;

/**
 * Class to test examples and discovered techniques.
 */
public class Examples {

	public Examples() {
		exp01();
	}

	public void exp01() {
		try {
			ProjectExplorer explorer = new ProjectExplorer("C:/Users/Abdelhamid/git/UMLGen/Fadel Abdelhamid - UML Diagrams Generator/bin");
			List<Class<?>> classList = explorer.getLoadedClasses();
			System.out.println("--Classes----------------------");
			for (Class<?> clazz : classList) {
				System.out.println(clazz.getName());
			}
			List<Package> packageList = explorer.getLoadedPackages();
			System.out.println("--Packages---------------------");
			for (Package pkg : packageList) {
				System.out.println(pkg.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
