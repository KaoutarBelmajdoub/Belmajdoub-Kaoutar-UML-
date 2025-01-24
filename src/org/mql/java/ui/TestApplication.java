package org.mql.java.ui;


import java.util.List;

import org.mql.java.jaxb.ObjectsToXml;
import org.mql.java.introspection.models.Annotation;
import org.mql.java.introspection.models.Class;
import org.mql.java.introspection.models.Constructor;
import org.mql.java.introspection.models.Field;
import org.mql.java.introspection.models.Interface;
import org.mql.java.introspection.models.Method;
import org.mql.java.introspection.models.Project;
import org.mql.java.introspection.models.Relation;
import org.mql.java.explorer.StructureScanner;
import org.mql.java.parser.XMLParser;

public class TestApplication {

    
    public TestApplication() {
        exp01();
    }

    private void exp01()  {
        StructureScanner memory = new StructureScanner();
        Project projet = memory.getProject("C:\\Program Files\\Java\\JavaProjet\\BelmajdoubKaoutar-Generics");
        ObjectsToXml xml = new ObjectsToXml(projet);
        xml.convert();
        XMLParser parser = new XMLParser();
        Project project = parser.parse("resources\\Project_Data.xml");  
        for (org.mql.java.introspection.models.Package pack : project.getPackages()) {
            System.out.println("*******************************************Package name is = " + pack.getPackage());
            for (Class classe : pack.getClasses()) {
                System.out.println("**********************************Class = " + classe.getClassName());
                System.out.println("**************************Fields");
                for (Field field : classe.getFields()) {
                    System.out.println(field.getModifier() + " " + field.getType() + " " + field.getName() );
                }
                System.out.println("**************************Methods");
                for (Method method : classe.getMethods()) {
                    System.out.println(method.getModifier() + " " + method.getReturnType() + " " + method.getName());
                }
                System.out.println("**************************Constructors");
                for (Constructor constructor : classe.getConstructors()) {
                    System.out.println(constructor.getModifier() + " " + constructor.getName());
                }
                System.out.println("**************************Relations");
                for (Relation relation : classe.getRelations()) {
                    System.out.println(relation.getClassName() + " " + relation.getName());
                }
            }
            List<Interface> interfaces = pack.getInterfaces();
            for (Interface classe : interfaces) {
                System.out.println("***************************************Interface = " + classe.getInterface());
                System.out.println("*****************************Methods************************");
                List<Method> methods = classe.getMethods();
                for (Method method : methods) {
                    System.out.println(method.getModifier() + " " + method.getReturnType() + " " + method.getName());
                }
                
            }   
            List<Annotation> annotations = pack.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("************************Annotation = " + annotation.getAnnotation()+"************************");
                System.out.println("************************Methods************************");
                List<Method> methods = annotation.getMethods();
                for (Method method : methods) {
                    System.out.println(method.getModifier() + " " + method.getReturnType() + " " + method.getName());
                }   
            }   
        }
    }
    
   
    public static void main(String[] args){
        
        new TestApplication();
    }
}
