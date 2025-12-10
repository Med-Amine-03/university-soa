
package com.soa.course.service;

import java.sql.SQLException;
import java.util.List;

import com.soa.course.dao.CourseDAO;
import com.soa.course.model.Course;

public class CourseService {
    private final CourseDAO dao = new CourseDAO();

    public Integer addCourse(Course c) throws SQLException {
        validate(c);
        return dao.insert(c);
    }

    
    public boolean updateCourse(Course c) throws SQLException {
        validate(c);
        if (c.getId() <= 0) throw new IllegalArgumentException("id must be > 0 for update");

        Course existing = dao.findById(c.getId());
        if (existing == null) {
            throw new IllegalArgumentException("Course id=" + c.getId() + " not found");
        }
        if (c.getName() == null) c.setName(existing.getName());
        if (c.getTeacher() == null) c.setTeacher(existing.getTeacher());
        
        return dao.update(c); // returns true if a row was updated
    }


    public boolean deleteCourse(int id) throws SQLException { return dao.delete(id); }

    public Course getCourse(int id) throws SQLException { return dao.findById(id); }

    public List<Course> listCourses() throws SQLException { return dao.findAll(); }

    private void validate(Course c) {
        if (c.getName() == null || c.getName().isBlank())
            throw new IllegalArgumentException("name is required");
        if (c.getCredits() < 0) throw new IllegalArgumentException("credits must be >= 0");
    }
}
