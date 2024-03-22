package auca.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_registration")
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id", nullable = false, updatable = false)
    private Long registrationId;

    @Column(name = "registration_code", length = 50)
    private String registrationCode;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private AcademicUnit department;

    public StudentRegistration() {
    }

    public StudentRegistration(String registrationCode, LocalDateTime registrationDate, Student student, Semester semester, AcademicUnit department) {
        this.registrationCode = registrationCode;
        this.registrationDate = registrationDate;
        this.student = student;
        this.semester = semester;
        this.department = department;
    }

    // Getters and Setters

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public AcademicUnit getDepartment() {
        return department;
    }

    public void setDepartment(AcademicUnit department) {
        this.department = department;
    }

    // toString method

    @Override
    public String toString() {
        return "StudentRegistration{" +
                "registrationId='" + registrationId + '\'' +
                ", registrationCode='" + registrationCode + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}