package org.mql.java.explorer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.mql.java.introspection.models.Relation;

public class ClassScanner {

	private Class<?> MyClass;

	public ClassScanner(Class<?> MyClass) {

		this.MyClass = MyClass;

	}

	public List<org.mql.java.introspection.models.Field> getProperties() {
		List<org.mql.java.introspection.models.Field> ListFields = new Vector<org.mql.java.introspection.models.Field>();
		Field[] fields = MyClass.getDeclaredFields();
		for (Field f : fields) {
			org.mql.java.introspection.models.Field field = new org.mql.java.introspection.models.Field();
			field.setModifier(Modifier.toString(f.getModifiers()));
			field.setType(f.getType().getName());
			field.setName(f.getName());
			ListFields.add(field);
		}
		return ListFields;
	}

	public List<org.mql.java.introspection.models.Method> getMethods() {
		List<org.mql.java.introspection.models.Method> Listmethods = new Vector<org.mql.java.introspection.models.Method>();
		Method[] methods = MyClass.getDeclaredMethods();
		for (Method methode : methods) {
			org.mql.java.introspection.models.Method method = new org.mql.java.introspection.models.Method();
			method.setModifier(Modifier.toString(methode.getModifiers()));
			method.setName(methode.getName());
			method.setReturnType(methode.getReturnType().getName());
			Listmethods.add(method);
		}
		return Listmethods;
	}

	public List<org.mql.java.introspection.models.Constructor> getConstructors() {
		List<org.mql.java.introspection.models.Constructor> constructors = new Vector<>();
		Constructor<?>[] AllConstructors = MyClass.getDeclaredConstructors();
		for (Constructor<?> constructor : AllConstructors) {
			org.mql.java.introspection.models.Constructor constructeur = new org.mql.java.introspection.models.Constructor();
			constructeur.setModifier(Modifier.toString(constructor.getModifiers()));
			constructeur.setName(constructor.getName());
			constructors.add(constructeur);
		}
		return constructors;
	}

	public List<Relation> getRelations() {

		List<Relation> relations = new Vector<Relation>();
		Field[] fields = MyClass.getDeclaredFields();
		Method[] methods = MyClass.getDeclaredMethods();
		Class<?> superClass = MyClass.getSuperclass();
		Class<?>[] interfaces = MyClass.getInterfaces();


		if (!superClass.equals(Object.class)) {
			Relation relation = new Relation();
			relation.setName("héritage");
			relation.setClassName(superClass.getSimpleName());
			relations.add(relation);
		}

		if (interfaces.length != 0) {
			for (Class<?> interfac : interfaces) {
				Relation relation = new Relation();
				relation.setName("realisation");
				relation.setClassName(interfac.getSimpleName());
				relations.add(relation);

			}
		}

		for (Field field : fields) {
			if (!field.getType().isPrimitive() && !field.getType().equals(String.class)) {
				Relation relation = new Relation();

				if (isFieldInitialized(MyClass, field)) {
					relation.setName("Composition");
					relation.setClassName(field.getType().getSimpleName());
				}

				else if (!isFieldInitialized(MyClass, field)) {
					relation.setName("Agrégation");
					relation.setClassName(field.getType().getSimpleName());

				}
				relations.add(relation);
			}
		}
		for (Method method : methods) {
			Parameter parameters[] = method.getParameters();
			for (Parameter parameter : parameters) {
				Relation relation = new Relation();
				if (!parameter.getType().isPrimitive()) {
					relation.setName("Dépendance");
					relation.setClassName(parameter.getType().getSimpleName());
				}
				relations.add(relation);
			}
			if (!method.getReturnType().isPrimitive()) {
				Relation relation = new Relation();
				relation.setName("Dépendance");
				relation.setClassName(method.getReturnType().getSimpleName());
				relations.add(relation);
			}
		}
		return relations;
	}

	private static boolean isFieldInitialized(Class<?> clazz, Field field) {
		try {
			field.setAccessible(true);
			return field.get(createInstance(clazz)) != null;
		} catch (Exception e) {
			System.out.println("Erreur " + e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public static Object createInstance(Class<?> clazz) {
		try {

			return clazz.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException e) {
			try {
				return clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException ex) {
				System.out.println("Erreur1 : " + ex.getMessage());
				return null;
			}
		} catch (Exception ex) {
			System.out.println("Erreur2 : " + ex.getMessage());
			return null;
		}
	}

}
