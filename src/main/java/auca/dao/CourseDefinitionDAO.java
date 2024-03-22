package auca.dao;

import auca.model.CourseDefinition;
import auca.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class CourseDefinitionDAO {

	public void InsertCourseDefinition(CourseDefinition courseDefinition) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.persist(courseDefinition);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	
    public CourseDefinition getCourseDefinitionById(Integer courseDefinitionId) {
        Transaction transaction = null;
        CourseDefinition courseDefinition = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            courseDefinition = session.get(CourseDefinition.class, courseDefinitionId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return courseDefinition;
    }

    public List<CourseDefinition> getAllCourseDefinitions() {
        Transaction transaction = null;
        List<CourseDefinition> courseDefinitions = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            courseDefinitions = session.createQuery("FROM CourseDefinition", CourseDefinition.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return courseDefinitions;
    }

    public void updateCourseDefinition(CourseDefinition courseDefinition) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(courseDefinition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCourseDefinitionById(Integer courseDefinitionId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CourseDefinition courseDefinition = session.get(CourseDefinition.class, courseDefinitionId);
            if (courseDefinition != null) {
                session.delete(courseDefinition);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}