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
			ProjectExplorer explorer = new ProjectExplorer("C:/repos/Java_MQL/Fadel Abdelhamid - StringMapper/bin");
			List<Class<?>> classList = explorer.getLoadedClasses();
			System.out.println("Classes---------------------");
			for (Class<?> clazz : classList) {
				System.out.println(clazz.getName());
			}
			Package[] packages = explorer.getClassloader().getDefinedPackages();
			System.out.println("Packages---------------------");
			for (Package pkg : packages) {
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
