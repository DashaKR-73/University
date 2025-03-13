package com.school.model.dto;

public class SubjectDto
        extends BasedDto<Long>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
