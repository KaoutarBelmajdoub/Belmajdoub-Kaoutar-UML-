package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Interface {

	private List<Method> methods;
	private String interfac;
	
	public Interface() {
		methods = new Vector<Method>();
	}
	@XmlElement(name = "method")
	public List<Method> getMethods() {
		return methods;
	}

	@XmlAttribute(name = "name",  required = true)
	public String getInterface() {
		return interfac;
	}

	public void setInterface(String interfac) {
		this.interfac = interfac;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	public void setMethod(Method method) {
		this.methods.add(method);
	}
	
}
