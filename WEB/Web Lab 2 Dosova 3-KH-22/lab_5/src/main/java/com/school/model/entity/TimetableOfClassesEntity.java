package com.school.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "time_of_classes")
public class TimetableOfClassesEntity
        extends BasedEntity<Long> {

    @ManyToOne(targetEntity = TeacherEntity.class)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne(targetEntity = StudyGradeEntity.class)
    @JoinColumn(name = "study_grade_id")
    private StudyGradeEntity studyGrade;

    @ManyToOne(targetEntity = StudentEntity.class)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @ManyToOne(targetEntity = ClassRoomEntity.class)
    @JoinColumn(name = "class_room_id")
    private ClassRoomEntity classRoom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_lesson")
    private Date startLesson;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_lesson")
    private Date endLesson;

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public StudyGradeEntity getStudyGrade() {
        return studyGrade;
    }

    public void setStudyGrade(StudyGradeEntity studyGrade) {
        this.studyGrade = studyGrade;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public ClassRoomEntity getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoomEntity classRoom) {
        this.classRoom = classRoom;
    }

    public Date getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(Date startLesson) {
        this.startLesson = startLesson;
    }

    public Date getEndLesson() {
        return endLesson;
    }

    public void setEndLesson(Date endLesson) {
        this.endLesson = endLesson;
    }
}
