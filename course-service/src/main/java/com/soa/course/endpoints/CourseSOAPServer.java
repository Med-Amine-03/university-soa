
package com.soa.course.endpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.ws.Endpoint;

public class CourseSOAPServer {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = CourseSOAPServer.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) props.load(in);
        } catch (IOException ignored) {}

        String port = props.getProperty("server.port", "8083");
        String context = props.getProperty("server.context", "/courseService");
        String address = "http://0.0.0.0:" + port + context;

        Endpoint.publish(address, new CourseEndpoint());
        System.out.println("Course SOAP Service published at " + address + "?wsdl");
        System.out.println("Press Ctrl+C to stop.");
    }
}
