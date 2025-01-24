package org.mql.java.explorer;
import org.mql.java.introspection.models.Annotation;
import org.mql.java.introspection.models.Class;
import org.mql.java.introspection.models.Enumeration;
import org.mql.java.introspection.models.Interface;
import org.mql.java.introspection.models.Package;
import org.mql.java.introspection.models.Project;

public class StructureScanner {

	private Project project;

	public StructureScanner() {
	}
	public Project getProject(String projectName) {
		String classpath = projectName + "\\bin";
		project = new Project();
		ProjectScanner projectE = new ProjectScanner();
		for (String pack : projectE.scan(projectName)) {
			Package packa = new Package();
			packa.setPackage(pack);
			project.setPackage(packa);
		}
		PackageScanner packE = new PackageScanner();
		for (Package pack : project.getPackages()) {
			for (java.lang.Class<?> clazz : packE.getClasses(pack.getPackage(), classpath)) {
				ClassScanner classE = new ClassScanner(clazz);
				Class NewClass = new Class();
				NewClass.setClassName(clazz.getSimpleName());
				NewClass.setFields(classE.getProperties());
				NewClass.setMethods(classE.getMethods());
				NewClass.setConstructors(classE.getConstructors());
				NewClass.setRelations(classE.getRelations());
				pack.setClasse(NewClass);
			}
			for (java.lang.Class<?> annotation : packE.getAnnotations(pack.getPackage(), classpath)) {
				ClassScanner classE = new ClassScanner(annotation);
				Annotation NewAnnotation = new Annotation();
				NewAnnotation.setAnnotation(annotation.getSimpleName());
				NewAnnotation.setMethods(classE.getMethods());
				pack.setAnnotation(NewAnnotation);
			}
			for (java.lang.Class<?> enumeration : packE.getEnumerations(pack.getPackage(), classpath)) {
				Enumeration newEnumeration = new Enumeration();
				newEnumeration.setEnumeration(enumeration.getSimpleName());
				pack.setEnumeration(newEnumeration);
			}
			for (java.lang.Class<?> Interface : packE.getInterfaces(pack.getPackage(), classpath)) {
				ClassScanner classE = new ClassScanner(Interface);
				Interface newInterface = new Interface();
				newInterface.setInterface(Interface.getSimpleName());
				newInterface.setMethods(classE.getMethods());
				pack.setInterface(newInterface);
			}
		}
		return project;
	}

}
