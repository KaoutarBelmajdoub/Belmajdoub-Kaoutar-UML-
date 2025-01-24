package org.mql.java.introspection.models;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Relation {

	private String name;
	private String className;
	
	@XmlAttribute(name = "name",  required = true)
	public String getName() {
		return name;
	}
	@XmlAttribute(name = "class",  required = true)
	public String getClassName() {
		return className;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
