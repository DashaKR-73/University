package com.school.model.dto;

import java.util.Date;

public class TimetableOfClassesDto
        extends BasedDto<Long> {

    private TeacherDto teacher;
    private StudyGradeDto studygrade;
    private SubjectDto subject;
    private ClassRoomDto classroom;
    private Date startlesson;
    private Date endlesson;

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public StudyGradeDto getStudygrade() {
        return studygrade;
    }

    public void setStudygrade(StudyGradeDto studygrade) {
        this.studygrade = studygrade;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public ClassRoomDto getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassRoomDto classroom) {
        this.classroom = classroom;
    }

    public Date getStartlesson() {
        return startlesson;
    }

    public void setStartlesson(Date startlesson) {
        this.startlesson = startlesson;
    }

    public Date getEndlesson() {
        return endlesson;
    }

    public void setEndlesson(Date endlesson) {
        this.endlesson = endlesson;
    }
}
