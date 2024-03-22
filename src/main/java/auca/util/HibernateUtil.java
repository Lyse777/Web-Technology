package auca.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import auca.model.*;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Database connection properties
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5433/AUCA_STUDENT_MANAGEMENT_MODULE_DB");
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "java");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(settings);

            // Entity classes
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(AcademicUnit.class);
            configuration.addAnnotatedClass(StudentRegistration.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(CourseDefinition.class);
            configuration.addAnnotatedClass(Semester.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}