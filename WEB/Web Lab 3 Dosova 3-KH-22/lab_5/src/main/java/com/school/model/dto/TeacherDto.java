package com.school.model.dto;

import java.util.List;

public class TeacherDto
        extends BasedDto<Long> {

    private PersonDto personDto;
    private TCategory category;
    private List<SubjectDto> subjects;

public enum TCategory {
    NONE,
    CATEGORY_ONE,
    CATEGORY_TWO,
    ;
}

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }

    public TCategory getCategory() {
        return category;
    }

    public void setCategory(TCategory category) {
        this.category = category;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
