package com.school.model.entity;


import com.school.controlller.TimetableOfClassesController;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class TeacherEntity
        extends BasedEntity<Long> {

        @OneToOne(targetEntity = PersonEntity.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinColumn(name = "person_id")
        private PersonEntity person;

        @ManyToMany(targetEntity = SubjectEntity.class)
        @JoinTable(name = "teacher_the_subject", joinColumns = @JoinColumn(name = "teacher_id"),
                inverseJoinColumns = @JoinColumn(name = "subject_id"))
        private Set<SubjectEntity> subjects;

        @OneToMany(targetEntity = TimetableOfClassesEntity.class, mappedBy = "studyGrade", fetch = FetchType.LAZY)
        private List<TimetableOfClassesEntity> timetableOfClasses;

        public PersonEntity getPerson() {
                return person;
        }

        public void setPerson(PersonEntity person) {
                this.person = person;
        }

        public Set<SubjectEntity> getSubjects() {
                return subjects;
        }

        public void setSubjects(Set<SubjectEntity> subjects) {
                this.subjects = subjects;
        }

        public List<TimetableOfClassesEntity> getTimetableOfClasses() {
                return timetableOfClasses;
        }

        public void setTimetableOfClasses(List<TimetableOfClassesEntity> timetableOfClasses) {
                this.timetableOfClasses = timetableOfClasses;
        }
}
