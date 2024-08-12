package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bidirectional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name= "student")
public class Student {
    @Column(name = "email", length = 50)
    @Id
    private String email;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @ToString.Exclude
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new LinkedHashSet<>();

       Student(String email, String name, String password, Set<Course> Courses) {

        this.email = email;
        this.name = name;
        this.password = password;
        this.courses = courses;
    }

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


}