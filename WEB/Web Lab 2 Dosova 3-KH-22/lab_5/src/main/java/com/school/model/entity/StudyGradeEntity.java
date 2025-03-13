package com.school.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "study_grades")
public class StudyGradeEntity extends BasedEntity<Long> {

    @Column(name = "code", length = 25)
    private String code;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.REMOVE)
    private List<StudentEntity> students;

    @OneToMany(mappedBy = "studyGrade", fetch = FetchType.LAZY)
    private List<TimetableOfClassesEntity> timetableOfClasses;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public List<TimetableOfClassesEntity> getTimetableOfClasses() {
        return timetableOfClasses;
    }

    public void setTimetableOfClasses(List<TimetableOfClassesEntity> timetableOfClasses) {
        this.timetableOfClasses = timetableOfClasses;
    }
}
