package org.mql.java.explorer;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;

public class PackageScanner extends ClassLoader {

	public PackageScanner() {
	}

	public List<Class<?>> getClasses(String packageName, String classPath) {
		return filterClasses(packageName, classPath, "Class");
	}

	public List<Class<?>> getAnnotations(String packageName, String classPath) {
		return filterClasses(packageName, classPath, "Annotation");
	}

	public List<Class<?>> getInterfaces(String packageName, String classPath) {
		return filterClasses(packageName, classPath, "Interface");
	}

	public List<Class<?>> getEnumerations(String packageName, String classPath) {
		return filterClasses(packageName, classPath, "Enumeration");
	}

	private List<Class<?>> filterClasses(String packageName, String classPath, String type) {

		List<Class<?>> filteredClasses = new Vector<>();

		try {
			ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { new URL("file://" + classPath + "/") },
					currentClassLoader);

			String packageFolder = packageName.replace('.', '\\');
			File dir = new File(classPath + '\\' + packageFolder);
			File classes[] = dir.listFiles();
			if (classes != null) {
				for (File f : classes) {
					String className = packageName + "." + f.getName().replace(".class", "");
					Class<?> myClass = classCharger(urlClassLoader, className);

					if ((myClass.isEnum() && "Enumeration".equals(type))
							|| (myClass.isAnnotation() && "Annotation".equals(type))
							|| (myClass.isInterface() && "Interface".equals(type) && !myClass.isAnnotation())
							|| (!myClass.isAnnotation() && !myClass.isEnum() && !myClass.isInterface()
									&& "Class".equals(type))) {
						filteredClasses.add(myClass);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Erreur = " + e.getMessage());
		}

		return filteredClasses;
	}

	private Class<?> classCharger(URLClassLoader urlClassLoader, String className) {
		try {
			return urlClassLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			System.out.println("Classe non trouvée : " + className);
			return null;
		}
	}
}
