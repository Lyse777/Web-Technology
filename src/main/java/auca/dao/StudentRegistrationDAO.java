package auca.dao;


import auca.model.AcademicUnit;
import auca.model.StudentRegistration;
import auca.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentRegistrationDAO {
    public void InsertStudentRegistration(StudentRegistration studentRegistration) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(studentRegistration);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public Long getDepartmentId(String departmentName) {
        Transaction transaction = null;
        Long departmentId = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            AcademicUnit department = (AcademicUnit) session.createQuery("FROM AcademicUnit WHERE departmentName = :name")
                    .setParameter("name", departmentName)
                    .uniqueResult();
            if (department != null) {
                departmentId = department.getDepartmentId();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return departmentId;
    }
    
    public List<StudentRegistration> getAllStudentRegistrations() {
        List<StudentRegistration> studentRegistrations = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            studentRegistrations = session.createQuery("FROM StudentRegistration", StudentRegistration.class)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentRegistrations;
    }
    
    public void deleteStudentRegistration(Long registrationId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            StudentRegistration studentRegistration = session.get(StudentRegistration.class, registrationId);
            if (studentRegistration != null) {
                session.delete(studentRegistration);
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Student registration with ID " + registrationId + " not found");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
        public void updateStudentRegistration(StudentRegistration studentRegistration) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.update(studentRegistration);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        
    }
        public StudentRegistration getStudentRegistrationById(Long registrationId) {
            Transaction transaction = null;
            StudentRegistration studentRegistration = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                studentRegistration = session.get(StudentRegistration.class, registrationId);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return studentRegistration;
        }
}