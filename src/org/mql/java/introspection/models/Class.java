package org.mql.java.introspection.models;
	import java.util.List;
	import java.util.Vector;
	import javax.xml.bind.annotation.XmlAttribute;
	import javax.xml.bind.annotation.XmlElement;
	import javax.xml.bind.annotation.XmlRootElement;
	@XmlRootElement
	public class Class {
		
		private List<Field> fields;
		private List<Method> methods;
		private List<Constructor> constructors;
		private String className;
		private  List<Relation> relations;
		
		public Class() {
			fields = new Vector<Field>();
			methods = new Vector<Method>();
			constructors = new Vector<Constructor>();
			relations = new Vector<Relation>();
		}
		 @XmlElement(name = "method")
		public List<Method> getMethods() {
			return methods;
		}
		@XmlElement(name = "constructor")
		public List<Constructor> getConstructors() {
			return constructors;
		}

		public void setMethods(List<Method> methods) {
			this.methods = methods;
		}

		public void setConstructors(List<Constructor> constructors) {
			this.constructors = constructors;
		}
		 @XmlElement(name = "field")
		public List<Field> getFields() {
			return fields;
		}
		 @XmlElement(name = "relation")
		public  List<Relation> getRelations() {
			return relations;
		}

		public void setFields(List<Field> fields) {
			this.fields = fields;
		}

		public void setRelations(List<Relation> relations) {
			this.relations = relations;
		}

		public void setRelation(Relation relation) {
			this.relations.add(relation);
		}

		public void setField(Field field) {
			fields.add(field);
		}

		public void setMethod(Method method) {
			this.methods.add(method);
		}

		public void setConstructor(Constructor constructor) {
			this.constructors.add(constructor);
		}
		@XmlAttribute(name = "name",  required = true)
		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		
		
	}


