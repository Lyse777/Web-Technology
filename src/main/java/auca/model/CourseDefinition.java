package auca.model;

import javax.persistence.*;

@Entity
@Table(name = "course_definition")
public class CourseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_definition_id", nullable = false, updatable = false)
    private Integer courseDefinitionId;

    @Column(name = "course_definition_code", length = 50)
    private String courseDefinitionCode;

    @Column(name = "course_definition_description", length = 50)
    private String courseDefinitionDescription;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseDefinition() {
    }

    public CourseDefinition(String courseDefinitionCode, String courseDefinitionDescription, Course course) {
        this.courseDefinitionCode = courseDefinitionCode;
        this.courseDefinitionDescription = courseDefinitionDescription;
        this.course = course;
    }

    // Getters and Setters

    public Integer getCourseDefinitionId() {
        return courseDefinitionId;
    }

    public void setCourseDefinitionId(Integer courseDefinitionId) {
        this.courseDefinitionId = courseDefinitionId;
    }

    public String getCourseDefinitionCode() {
        return courseDefinitionCode;
    }

    public void setCourseDefinitionCode(String courseDefinitionCode) {
        this.courseDefinitionCode = courseDefinitionCode;
    }

    public String getCourseDefinitionDescription() {
        return courseDefinitionDescription;
    }

    public void setCourseDefinitionDescription(String courseDefinitionDescription) {
        this.courseDefinitionDescription = courseDefinitionDescription;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // toString method

    @Override
    public String toString() {
        return "CourseDefinition{" +
                "courseDefinitionId='" + courseDefinitionId + '\'' +
                ", courseDefinitionCode='" + courseDefinitionCode + '\'' +
                ", courseDefinitionDescription='" + courseDefinitionDescription + '\'' +
                '}';
    }
}