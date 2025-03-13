package com.school.controlller;

import com.school.controlller.ICrudController;
import com.school.model.dto.ClassRoomDto;
import com.school.model.entity.ClassRoomEntity;
import com.school.repository.ClassRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("classrooms")
public class ClassRoomController implements ICrudController<ClassRoomDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassRoomController.class);

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public List<ClassRoomDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх класів");
        List<ClassRoomEntity> entityList = classRoomRepository.findAll();
        List<ClassRoomDto> dtoList = new ArrayList<>(entityList.size());

        for (ClassRoomEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public ClassRoomDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання класу за ідентифікатором {}", id);
        ClassRoomDto dto = null;
        Optional<ClassRoomEntity> preloadOptional = classRoomRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення класу за ідентифікатором {}", id);
        classRoomRepository.deleteById(id);
    }

    @Override
    public ClassRoomDto saveOrUpdate(ClassRoomDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення класу {}",
                dto.getCode());
        ClassRoomEntity preload;
        if (dto.getId() == null) {
            preload = new ClassRoomEntity();
        } else {
            Optional<ClassRoomEntity> preloadOptional = classRoomRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(ClassRoomEntity::new);
        }
        preload.setCode(dto.getCode());

        preload = classRoomRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private ClassRoomDto buildDto(ClassRoomEntity entity) {
        ClassRoomDto dto = new ClassRoomDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());

        return dto;
    }
}

