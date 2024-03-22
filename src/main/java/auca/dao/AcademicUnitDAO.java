package auca.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import auca.model.AcademicUnit;
import auca.util.HibernateUtil;

public class AcademicUnitDAO {
	public void insertAcademicUnit(AcademicUnit academicUnit) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(academicUnit);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}
	public AcademicUnit getAcademicUnitById(Long departmentId) {
        Transaction transaction = null;
        AcademicUnit academicUnit = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            academicUnit = session.get(AcademicUnit.class, departmentId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return academicUnit;
    }
	
	
    public AcademicUnit getAcademicUnitById(Long academicUnitId, Session session) {
        AcademicUnit academicUnit = null;
        try {
            academicUnit = session.get(AcademicUnit.class, academicUnitId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return academicUnit;
    }
	
	public List<AcademicUnit> getAllAcademicUnits() {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery("FROM AcademicUnit", AcademicUnit.class).list();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	   
	   public AcademicUnit getAcademicUnitByName(String academicUnitName) {
		    Transaction transaction = null;
		    AcademicUnit academicUnit = null;
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        transaction = session.beginTransaction();
		        String hql = "FROM AcademicUnit WHERE academicName = :name";
		        academicUnit = session.createQuery(hql, AcademicUnit.class)
		                .setParameter("name", academicUnitName)
		                .uniqueResult();
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		    return academicUnit;
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
	
public void deleteAcademicUnit(long academicId) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        AcademicUnit academicUnit = session.get(AcademicUnit.class, academicId);
        if (academicUnit != null) {
            session.delete(academicUnit);
            transaction.commit();
        }
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}

public void updateAcademicUnit(AcademicUnit academicUnit) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.update(academicUnit);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
}

