package com.school.model.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class SubjectEntity
        extends BasedEntity<Long> {

    @Column(name = "name", length = 75)
    private String name;

    @ManyToMany(targetEntity = TeacherEntity.class, mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<TeacherEntity> teacher;

    @ManyToMany(targetEntity = TimetableOfClassesEntity.class, mappedBy = "subject", fetch = FetchType.LAZY)
    private List<TimetableOfClassesEntity> timetableOfClasses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeacherEntity> getTeacher() {
        return teacher;
    }

    public void setTeacher(Set<TeacherEntity> teacher) {
        this.teacher = teacher;
    }

    public List<TimetableOfClassesEntity> getTimetableOfClasses() {
        return timetableOfClasses;
    }

    public void setTimetableOfClasses(List<TimetableOfClassesEntity> timetableOfClasses) {
        this.timetableOfClasses = timetableOfClasses;
    }
}
