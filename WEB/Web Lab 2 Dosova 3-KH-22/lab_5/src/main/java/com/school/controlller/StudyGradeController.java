package com.school.controlller;

import com.school.model.dto.StudyGradeDto;
import com.school.model.entity.StudyGradeEntity;
import com.school.repository.StudyGradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("grades")
public class StudyGradeController implements ICrudController<StudyGradeDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyGradeController.class);

    @Autowired
    private StudyGradeRepository studyGradeRepository;

    @Override
    public List<StudyGradeDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх класів");

        List<StudyGradeEntity> entityList = studyGradeRepository.findAll();
        List<StudyGradeDto> dtoList = new ArrayList<>(entityList.size());

        for (StudyGradeEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public StudyGradeDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання класу за ідентифікатором {}", id);
        StudyGradeDto dto = null;
        Optional<StudyGradeEntity> preloadOptional = studyGradeRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення класу за ідентифікатором {}", id);
        studyGradeRepository.deleteById(id);
    }

    @Override
    public StudyGradeDto saveOrUpdate(StudyGradeDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення класу {} {}",
                dto.getCode(), dto.getStudent());
        StudyGradeEntity preload;
        if (dto.getId() == null) {
            preload = new StudyGradeEntity();
        } else {
            Optional<StudyGradeEntity> preloadOptional = studyGradeRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(StudyGradeEntity::new);
        }
        preload.setCode(dto.getCode());
        // Assuming dto.getStudent() returns the student name or some relevant field
        // If it's an object, map it accordingly

        preload = studyGradeRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private StudyGradeDto buildDto(StudyGradeEntity entity) {
        StudyGradeDto dto = new StudyGradeDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        // If there are more fields in StudyGradeDto, set them here

        return dto;
    }
}
