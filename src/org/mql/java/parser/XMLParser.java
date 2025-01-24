package org.mql.java.parser;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.mql.java.introspection.models.Annotation;
import org.mql.java.introspection.models.Class;
import org.mql.java.introspection.models.Constructor;
import org.mql.java.introspection.models.Field;
import org.mql.java.introspection.models.Interface;
import org.mql.java.introspection.models.Method;
import org.mql.java.introspection.models.Package;
import org.mql.java.introspection.models.Project;
import org.mql.java.introspection.models.Relation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {

	private Project project = new Project();
	private boolean isProject = false;
	private Package currentPackage = null;
	private Class currentClass = null;
	private Annotation currentAnnotation = null;
	private Interface currentInterface = null;
	private Method currentMethod = null;
	private Constructor currentConstructor = null;
	private Field currentField = null;
	private Relation currentRelation = null;

	public Project getProject() {
		return project;
	}

	public Project parse(String src) {
		SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(src, this);
		} catch (Exception e) {
			System.out.println("Erreur = " + e.getMessage());
		}
		return project;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("project")) {
			isProject = true;
		} else if (qName.equalsIgnoreCase("package")) {
			currentPackage = new Package();
			currentPackage.setPackage(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("classe")) {
			currentClass = new Class();
			currentClass.setClassName(attributes.getValue("name"));

		} else if (qName.equalsIgnoreCase("annotation")) {
			currentAnnotation = new Annotation();
			currentAnnotation.setAnnotation(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("interface")) {
			currentInterface = new Interface();
			currentInterface.setInterface(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("method")) {
			currentMethod = new Method();
			currentMethod.setModifier(attributes.getValue("modifier"));
			currentMethod.setReturnType(attributes.getValue("returnType"));
			currentMethod.setName(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("constructor")) {
			currentConstructor = new Constructor();
			currentConstructor.setModifier(attributes.getValue("modifier"));
			currentConstructor.setName(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("field")) {
			currentField = new Field();
			currentField.setModifier(attributes.getValue("modifier"));
			currentField.setType(attributes.getValue("type"));
			currentField.setName(attributes.getValue("name"));
		} else if (qName.equalsIgnoreCase("relation")) {
			currentRelation = new Relation();
			currentRelation.setClassName(attributes.getValue("class"));
			currentRelation.setName(attributes.getValue("name"));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// Manipulation des données du texte si nécessaire
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("project")) {
			isProject = false;
		} else if (qName.equalsIgnoreCase("package")) {
			project.setPackage(currentPackage);
		} else if (qName.equalsIgnoreCase("classe")) {
			currentPackage.setClasse(currentClass);
		} else if (qName.equalsIgnoreCase("field") && currentClass != null) {
			currentClass.setField(currentField);
		} else if (qName.equalsIgnoreCase("annotation")) {
			currentPackage.setAnnotation(currentAnnotation);
		} else if (qName.equalsIgnoreCase("interface")) {
			currentPackage.setInterface(currentInterface);
		} else if (qName.equalsIgnoreCase("method") && currentClass != null) {
			currentClass.setMethod(currentMethod);
		} else if (qName.equalsIgnoreCase("method") && currentInterface != null) {
			currentInterface.setMethod(currentMethod);
		} else if (qName.equalsIgnoreCase("method") && currentAnnotation != null) {
			currentAnnotation.setMethod(currentMethod);
		} else if (qName.equalsIgnoreCase("constructor")) {
			if (currentClass != null) {
				currentClass.setConstructor(currentConstructor);
			}
		} else if (qName.equalsIgnoreCase("relation")) {
			if (currentClass != null) {
				currentClass.setRelation(currentRelation);
			}
		}
	}
}
