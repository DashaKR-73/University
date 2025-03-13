package com.school.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class StudentEntity extends BasedEntity<Long> {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "study_grade_id")
    private StudyGradeEntity grade;

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public StudyGradeEntity getGrade() {
        return grade;
    }

    public void setGrade(StudyGradeEntity grade) {
        this.grade = grade;
    }
}
