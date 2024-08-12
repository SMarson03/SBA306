package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.Set;


/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "instructor", length = 50, nullable = false)
    private String instructor;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_email"))
    Set<Student> students = new LinkedHashSet<>();

    Course(String name, String instructor, Set<Student> students) {
        this.name = name;
        this.instructor = instructor;
        this.students = students;

    }


    public Course(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;

    }

    public Course() {

    }

    // GETTERS AND SETTER

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && name.equals(course.name) && instructor.equals(course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructor);
    }

}

