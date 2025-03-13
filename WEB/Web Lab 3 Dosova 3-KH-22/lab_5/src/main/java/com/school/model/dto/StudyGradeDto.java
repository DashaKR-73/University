package com.school.model.dto;

import java.util.List;

public class StudyGradeDto
        extends BasedDto<Long> {

    private String code;
    private List<StudentDto> student;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<StudentDto> getStudent() {
        return student;
    }

    public void setStudent(List<StudentDto> student) {
        this.student = student;
    }
}
