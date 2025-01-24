package org.mql.java.jaxb;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.mql.java.explorer.StructureScanner;
import org.mql.java.introspection.models.Project;

public class ObjectsToXml {

	Project project;
	public ObjectsToXml(Project project) {
		this.project = project;
	}
	public void convert() {
		StructureScanner structer = new StructureScanner();
		File outputFile = new File("resources\\Project_Data.xml");
		try {
			JAXBContext context = JAXBContext.newInstance(Project.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this.project, outputFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
