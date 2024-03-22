package auca.model;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false, updatable = false)
    private Long teacherId;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualification", length = 50)
    private EQualification qualification;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "tutor_role")
    private ETutorRole tutorRole;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, EQualification qualification, Course course, ETutorRole tutorRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.qualification = qualification;
        this.course = course;
        this.tutorRole = tutorRole;
    }

    // Getters and Setters

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EQualification getQualification() {
        return qualification;
    }

    public void setQualification(EQualification qualification) {
        this.qualification = qualification;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ETutorRole getTutorRole() {
        return tutorRole;
    }

    public void setTutorRole(ETutorRole tutorRole) {
        this.tutorRole = tutorRole;
    }

    // toString method

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", qualification=" + qualification +
                ", tutorRole=" + tutorRole +
                '}';
    }
}