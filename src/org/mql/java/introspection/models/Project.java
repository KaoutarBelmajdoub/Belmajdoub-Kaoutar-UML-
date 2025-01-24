package org.mql.java.introspection.models;


import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {
	
	private List<Package> packages;
	
	
	public Project() {
		packages = new Vector<Package>();
	}

	 @XmlElement(name = "package")
	public List<Package> getPackages() {
		return packages;
	}


	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	
	public void setPackage(Package Package) {
		this.packages.add(Package);
	}
	

}
