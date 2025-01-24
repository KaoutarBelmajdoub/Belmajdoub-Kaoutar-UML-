package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Annotation {

	private String annotation;
	private List<Method> methods;

	public Annotation() {
		methods = new Vector<Method>();
	}

	@XmlAttribute(name = "name", required = true)
	public String getAnnotation() {
		return annotation;
	}

	@XmlElement(name = "method")
	public List<Method> getMethods() {
		return methods;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public void setMethod(Method method) {
		this.methods.add(method);
	}

}
