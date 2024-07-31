package run.api.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "classname", nullable = false, length = 50)
    private String classname;

    @Column(name = "major", nullable = false, length = 50)
    private String major;

    @Column(name = "hometown", nullable = false, length = 50)
    private String hometown;

    @Column(name = "gender", nullable = false, length = 20)
    private String gender;

    @Column(name = "avgmark", nullable = false)
    private Double avgmark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getAvgmark() {
        return avgmark;
    }

    public void setAvgmark(Double avgmark) {
        this.avgmark = avgmark;
    }

}