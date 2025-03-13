package com.school.controlller;

import com.school.model.dto.TeacherDto;
import com.school.model.entity.TeacherEntity;
import com.school.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("teachers")
public class TeacherController implements ICrudController<TeacherDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх вчителів");

        List<TeacherEntity> entityList = teacherRepository.findAll();
        List<TeacherDto> dtoList = new ArrayList<>(entityList.size());

        for (TeacherEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public TeacherDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання вчителя за ідентифікатором {}", id);
        TeacherDto dto = null;
        Optional<TeacherEntity> preloadOptional = teacherRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення вчителя за ідентифікатором {}", id);
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDto saveOrUpdate(TeacherDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення вчителя {} {} {}",
                dto.getPersonDto(), dto.getCategory(), dto.getSubjects());
        TeacherEntity preload;
        if (dto.getId() == null) {
            preload = new TeacherEntity();
        } else {
            Optional<TeacherEntity> preloadOptional = teacherRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(TeacherEntity::new);
        }
        preload = teacherRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private TeacherDto buildDto(TeacherEntity entity) {
        TeacherDto dto = new TeacherDto();
        return dto;
    }
}
