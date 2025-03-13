package com.school.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class_room")
public class ClassRoomEntity
        extends BasedEntity<Long> {

    @Column(name = "code", length = 20)
    private String code;

    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TimetableOfClassesEntity> timetableOfClasses;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TimetableOfClassesEntity> getTimetableOfClasses() {
        return timetableOfClasses;
    }

    public void setTimetableOfClasses(List<TimetableOfClassesEntity> timetableOfClasses) {
        this.timetableOfClasses = timetableOfClasses;
    }
}
