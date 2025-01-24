package org.mql.java.introspection.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Field {

	private String modifier ;
	private String name ;
	private String type ;
	
	@XmlAttribute(name = "modifier",  required = true)
	public String getModifier() {
		return modifier;
	}
	@XmlAttribute(name = "name",  required = true)
	public String getName() {
		return name;
	}
	@XmlAttribute(name = "type",  required = true)
	public String getType() {
		return type;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
