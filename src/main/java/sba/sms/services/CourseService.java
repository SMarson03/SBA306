package sba.sms.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */

public class CourseService implements CourseI {
    @Override
    public void createCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = null;

        try {
            trans = session.beginTransaction();
            session.persist(course);
            trans.commit();
        } catch (HibernateException exception) {
            if(trans != null)
                trans.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = null;
        Course course = new Course();
        try {
            trans = session.beginTransaction();
            Query<Course> q = session.createQuery("from Course where id = :id", Course.class);
            q.setParameter("id",courseId);
            course = q.getSingleResult();
            trans.commit();
        } catch (HibernateException ex) {
            if(trans != null)
                trans.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = null;
        List<Course> courseList = new ArrayList<>();
        try {
            trans = session.beginTransaction();
            Query<Course> q = session.createQuery("from Course ", Course.class);
            courseList = q.getResultList();
            trans.commit();
        } catch (HibernateException ex) {
            if (trans != null)
                trans.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return courseList;
    }
    }


