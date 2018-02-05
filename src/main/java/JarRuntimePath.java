import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class JarRuntimePath {
    public static void main(String[] args) throws MalformedURLException {

        // Example using InvokerTransformer from Apache Commons Collection
        Class theClass = InvokerTransformer.class;

        // Find the path of the compiled class
        String classPath = theClass.getResource(theClass.getSimpleName() + ".class").toString();
        System.out.println("Class: " + classPath);

        // Find the path of the lib which includes the class
        String libPath = classPath.substring(0, classPath.lastIndexOf("!"));
        System.out.println("Lib:   " + libPath);

        // Find the path of the file inside the lib jar
        String filePath = libPath + "!/META-INF/MANIFEST.MF";
        System.out.println("File:  " + filePath);

        // We look at the manifest file, getting two attributes out of it
        Manifest manifest = null;
        try {
            manifest = new Manifest(new URL(filePath).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Attributes attr = manifest.getMainAttributes();
        System.out.println("Manifest-Version: " + attr.getValue("Manifest-Version"));
        System.out.println("Implementation-Version: " + attr.getValue("Implementation-Version"));
    }
}

/*
* OUTPUT :
Class: jar:file:/C:/Users/jasleen/.m2/repository/org/apache/commons/commons-collections4/4.1/commons-collections4-4.1.jar!/org/apache/commons/collections4/functors/InvokerTransformer.class
Lib:   jar:file:/C:/Users/jasleen/.m2/repository/org/apache/commons/commons-collections4/4.1/commons-collections4-4.1.jar
File:  jar:file:/C:/Users/jasleen/.m2/repository/org/apache/commons/commons-collections4/4.1/commons-collections4-4.1.jar!/META-INF/MANIFEST.MF
Manifest-Version: 1.0
Implementation-Version: 4.1
* */