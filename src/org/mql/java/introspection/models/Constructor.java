package org.mql.java.introspection.models;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Constructor {

	private String modifier ;
	private String name ;
	
	@XmlAttribute(name = "modifier",  required = true)
	public String getModifier() {
		return modifier;
	}
	@XmlAttribute(name = "name",  required = true)
	public String getName() {
		return name;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
