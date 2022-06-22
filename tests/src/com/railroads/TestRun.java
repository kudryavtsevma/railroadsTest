package com.railroads;

import com.railroads.utils.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Утилита, которая запускает все методы помеченные анотацией @Test в классах, которые указаны в TestRun.xml
 */
public class TestRun {

    public static void main(String[] args) throws Exception {
        String pathInString = String.format("TestRun-%s.txt", UUID.randomUUID());
        File output = new File(pathInString);
        
        ArrayList<String> resultsTestRun = new ArrayList<>();
        
        ArrayList<String> classes = getClasses();
        int allTests = 0; // счетчик общего количества тестов
        int successTest = 0; // счетчик успешных тестов
        String caseResult = null;

        for (String className : classes) {
            Class<?> tests = Class.forName(className);

            for (Method method : tests.getDeclaredMethods()) {
                Annotation testAnnotation = method.getAnnotation(Test.class);
                Test test = (Test) testAnnotation;
                if (test != null) {
                    allTests++;
                    try {
                         method.invoke(tests
                                .getDeclaredConstructor()
                                .newInstance());
                        caseResult = method.getName() + " [description: " + test.value() + "]" + " SUCCESS ";
                        System.out.println("\u001B[42m" + caseResult + "\u001B[42m");
                        successTest++;
                    } catch (Throwable ex) {
                        caseResult = method.getName() + " [description: " + test.value() + "]" + " FAILED: " + ex.getCause();
                        System.out.println("\u001B[41m" + caseResult + "\u001B[41m");
                    }
                }
                resultsTestRun.add(caseResult);
            }
        }
        resultsTestRun.add("=================================================");
        resultsTestRun.add(" ");

        String total = "Test results: " + successTest + " SUCCESS out of " + allTests;
        resultsTestRun.add(total);
        System.out.println("\u001B[0m" + total + "\u001B[0m");

        Files.write(Path.of(pathInString), resultsTestRun, StandardCharsets.UTF_8);
    }

    /**
     * Метод получения имен классов из xml
     */
    public static ArrayList<String> getClasses() throws
            ParserConfigurationException, IOException, SAXException, ClassNotFoundException {
        ArrayList<String> allClasses = new ArrayList<>();

        DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dBFactory.newDocumentBuilder();
        Document document = builder.parse(new File("tests/src/com/railroads/TestRun.xml"));
        NodeList testsClasses = document.getElementsByTagName("test");
        for (int i = 0; i < testsClasses.getLength(); i++) {
            Element testClass = (Element) testsClasses.item(i);
            allClasses.add(testClass.getTextContent());
        }
        return allClasses;
    }

    public static void step(String stepName) {
        System.out.println("\u001B[0m" + "- "+stepName + "\u001B[0m");
    }
}
