package com.school.repository;

import com.school.model.entity.StudyGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGradeRepository
        extends JpaRepository<StudyGradeEntity, Long> {
}
