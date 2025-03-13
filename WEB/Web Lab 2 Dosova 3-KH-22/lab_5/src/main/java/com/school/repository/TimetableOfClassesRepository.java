package com.school.repository;

import com.school.model.entity.TimetableOfClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableOfClassesRepository
        extends JpaRepository<TimetableOfClassesEntity, Long> {
}
