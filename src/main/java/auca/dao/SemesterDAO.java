package auca.dao;

import auca.model.Semester;
import auca.util.HibernateUtil;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SemesterDAO {

	 public void InsertSemester(Semester semester) {
	        Session session = null;
	        Transaction transaction = null;
	        try {
	            session = HibernateUtil.getSessionFactory().openSession();
	            transaction = session.beginTransaction();
	            session.save(semester);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }
	    }

	 
	 public Semester getSemesterById(Integer semesterId) {
		    Transaction transaction = null;
		    Semester semester = null;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        semester = session.get(Semester.class, semesterId);
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		    return semester;
		}
	 
	 public List<Semester> getAllSemesters() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.createQuery("FROM Semester", Semester.class).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 public void deleteSemesterById(Integer semesterId) {
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        Transaction transaction = session.beginTransaction();
		        Semester semester = session.get(Semester.class, semesterId);
		        if (semester != null) {
		            session.delete(semester);
		            transaction.commit();
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	 
	 public void updateSemester(Semester semester) {
		    Transaction transaction = null;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        session.update(semester);
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		}
	 public Semester getSemesterByName(String semesterName) {
		    Transaction transaction = null;
		    Semester semester = null;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        Query<Semester> query = session.createQuery("FROM Semester WHERE semesterName = :semesterName", Semester.class);
		        query.setParameter("semesterName", semesterName);
		        semester = query.uniqueResult();
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		    return semester;
		}
}

