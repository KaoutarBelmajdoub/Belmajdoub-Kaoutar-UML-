package org.mql.java.introspection.models;

import java.util.List;
import java.util.Vector;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Package {

	private List<Class> classes;
	private List<Annotation> annotations;
	private List<Enumeration> enumerations;
	private List<Interface> interfaces;
	private String pack;

	public Package() {
		classes = new Vector<>();
		annotations = new Vector<>();
		enumerations = new Vector<>();
		interfaces = new Vector<>();
		pack = new String();
	}

	@XmlElement(name = "classe")
	public List<Class> getClasses() {
		return classes;
	}

	@XmlElement(name = "annotation")
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	@XmlElement(name = "enumeration")
	public List<Enumeration> getEnumerations() {
		return enumerations;
	}

	@XmlElement(name = "interface")
	public List<Interface> getInterfaces() {
		return interfaces;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public void setEnumerations(List<Enumeration> enumerations) {
		this.enumerations = enumerations;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	@XmlAttribute(name = "name", required = true)
	public String getPackage() {
		return pack;
	}

	public void setPackage(String pack) {
		this.pack = pack;
	}

	public void setClasse(Class classe) {
		this.classes.add(classe);
	}

	public void setAnnotation(Annotation annotation) {
		this.annotations.add(annotation);
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumerations.add(enumeration);
	}

	public void setInterface(Interface interfaze) {
			this.interfaces.add(interfaze);
		}}
