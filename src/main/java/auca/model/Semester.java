package auca.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "semester")
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "semester_id", nullable = false, updatable = false)
	private Integer semesterId;

    @Column(name = "semester_name", length = 50)
    private String semesterName;

    @Column(name = "starting_date")
    private LocalDateTime startingDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Semester() {
    }

    public Semester(String semesterName, LocalDateTime startingDate, LocalDateTime endDate) {
        this.semesterName = semesterName;
        this.startingDate = startingDate;
        this.endDate = endDate;
    }

    // Getters and Setters

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public LocalDateTime getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // toString method

    @Override
    public String toString() {
        return "Semester{" +
                "semesterId='" + semesterId + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", startingDate=" + startingDate +
                ", endDate=" + endDate +
                '}';
    }
}