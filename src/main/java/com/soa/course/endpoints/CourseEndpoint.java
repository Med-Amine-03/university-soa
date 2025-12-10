
package com.soa.course.endpoints;

import com.soa.course.model.Course;
import com.soa.course.service.CourseService;
import com.soa.course.util.ResponseMessage;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "CourseService", targetNamespace = "http://course.soa.com/")
public class CourseEndpoint {
    private final CourseService service = new CourseService();

    @WebMethod(operationName = "addCourse")
    @WebResult(name = "result")
    public ResponseMessage addCourse(@WebParam(name = "course") Course course) {
        try {
            Integer id = service.addCourse(course);
            return new ResponseMessage(true, "Created with id=" + id);
        } catch (IllegalArgumentException iae) {
            return new ResponseMessage(false, iae.getMessage());
        } catch (SQLException e) {
            return new ResponseMessage(false, "Database error: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "updateCourse")
    @WebResult(name = "result")
    public ResponseMessage updateCourse(@WebParam(name = "course") Course course) {
        try {
            boolean ok = service.updateCourse(course);
            return new ResponseMessage(ok, ok ? "Updated" : "Not found");
        } catch (IllegalArgumentException iae) {
            return new ResponseMessage(false, iae.getMessage());
        } catch (SQLException e) {
            return new ResponseMessage(false, "Database error: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "deleteCourse")
    @WebResult(name = "result")
    public ResponseMessage deleteCourse(@WebParam(name = "id") int id) {
        try {
            boolean ok = service.deleteCourse(id);
            return new ResponseMessage(ok, ok ? "Deleted" : "Not found");
        } catch (SQLException e) {
            return new ResponseMessage(false, "Database error: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listCourses")
    @WebResult(name = "courses")
    public List<Course> listCourses() {
        try {
            return service.listCourses();
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    @WebMethod(operationName = "getCourse")
    @WebResult(name = "course")
    public Course getCourse(@WebParam(name = "id") int id) {
        try {
            return service.getCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }
}
