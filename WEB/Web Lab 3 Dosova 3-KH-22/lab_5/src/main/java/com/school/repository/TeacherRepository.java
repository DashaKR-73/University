package com.school.repository;

import com.school.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository
        extends JpaRepository<TeacherEntity,Long>{
}
