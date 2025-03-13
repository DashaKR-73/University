package com.school.model.dto;

public class ClassRoomDto
        extends BasedDto<Long> {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
