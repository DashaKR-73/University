package com.school.model.dto;

import com.school.model.entity.StudyGradeEntity;

public class StudentDto
        extends BasedDto<Long>{

    private PersonDto personDto;
    private StudyGradeDto grade;

    public enum SGrade{
        FIRST_CLASS,
        SECOND_CLASS,
        ;
    }

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }

    public StudyGradeDto getGrade() {
        return grade;
    }

    public void setGrade(StudyGradeDto grade) {
        this.grade = grade;
    }
}
