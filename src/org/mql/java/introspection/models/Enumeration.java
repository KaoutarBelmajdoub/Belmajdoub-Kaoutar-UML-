package org.mql.java.introspection.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Enumeration {

	private String enumeration;
	
	public Enumeration() {
	}
	@XmlAttribute(name = "name",  required = true)
	public String getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}
	
	
}

