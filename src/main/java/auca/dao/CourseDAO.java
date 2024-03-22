package auca.dao;

import auca.model.Course;

import auca.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseDAO {

	public void InsertCourse(Course course) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.persist(course);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}

	public Course getCourseById(Integer courseId) {
	    Transaction transaction = null;
	    Course course = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        course = session.get(Course.class, courseId);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return course;
	}
	
	   public Course getCourseById(int courseId, Session session) {
	        Course course = null;
	        try {
	            course = session.get(Course.class, courseId);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return course;
	    }

	
	public List<Course> getAllCourses() {
        Transaction transaction = null;
        List<Course> courses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            courses = session.createQuery("FROM Course", Course.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return courses;
    }
	
	 public void deleteCourseById(int courseId) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            Course course = session.get(Course.class, courseId);
	            if (course != null) {
	                session.delete(course);
	                transaction.commit();
	            } else {
	                System.out.println("Course not found with ID: " + courseId);
	            }
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	    }
	 
	 public void updateCourse(Course course) {
		    Transaction transaction = null;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        session.update(course);
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		}

}
