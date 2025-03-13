package com.school.repository;

import com.school.model.entity.ClassRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository
        extends JpaRepository<ClassRoomEntity,Long> {
}

