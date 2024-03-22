package auca.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "academic_unit")
public class AcademicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academic_id", nullable = false, updatable = false)
    private Long academicId;

    @Column(name = "academic_code", length = 50)
    private String academicCode;

    @Column(name = "academic_name", length = 50)
    private String academicName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50)
    private EAcademicUnit type;

    @Column(name = "department_id")
    private Long departmentId = 2L; // Default department ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AcademicUnit parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AcademicUnit> children = new HashSet<>();

    @OneToMany(mappedBy = "academicUnit")
    private Set<Student> students;

    public AcademicUnit() {
    }

    public AcademicUnit(String academicCode, String academicName, EAcademicUnit type) {
        this.academicCode = academicCode;
        this.academicName = academicName;
        this.type = type;
    }

    // Getters and Setters

    public Long getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }

    public String getAcademicCode() {
        return academicCode;
    }

    public void setAcademicCode(String academicCode) {
        this.academicCode = academicCode;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public EAcademicUnit getType() {
        return type;
    }

    public void setType(EAcademicUnit type) {
        this.type = type;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public AcademicUnit getParent() {
        return parent;
    }

    public void setParent(AcademicUnit parent) {
        this.parent = parent;
    }

    public Set<AcademicUnit> getChildren() {
        return children;
    }

    public void setChildren(Set<AcademicUnit> children) {
        this.children = children;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // Set parent ID method
    public void setParentId(long parentId) {
        this.parent = new AcademicUnit();
        this.parent.setAcademicId(parentId);
    }

    // toString method

    @Override
    public String toString() {
        return "AcademicUnit{" +
                "academicId=" + academicId +
                ", academicCode='" + academicCode + '\'' +
                ", academicName='" + academicName + '\'' +
                ", type=" + type +
                '}';
    }
}
