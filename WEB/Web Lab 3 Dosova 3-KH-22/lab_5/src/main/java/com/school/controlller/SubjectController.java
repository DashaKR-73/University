package com.school.controlller;

import com.school.model.dto.SubjectDto;
import com.school.model.entity.SubjectEntity;
import com.school.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subjects")
public class SubjectController implements ICrudController<SubjectDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх предметів");

        List<SubjectEntity> entityList = subjectRepository.findAll();
        List<SubjectDto> dtoList = new ArrayList<>(entityList.size());

        for (SubjectEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public SubjectDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання предмета за ідентифікатором {}", id);
        SubjectDto dto = null;
        Optional<SubjectEntity> preloadOptional = subjectRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення предмета за ідентифікатором {}", id);
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDto saveOrUpdate(SubjectDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення предмета {}", dto.getName());
        SubjectEntity preload;
        if (dto.getId() == null) {
            preload = new SubjectEntity();
        } else {
            Optional<SubjectEntity> preloadOptional = subjectRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(SubjectEntity::new);
        }
        preload.setName(dto.getName());
        // If there are more fields in SubjectDto, set them here

        preload = subjectRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private SubjectDto buildDto(SubjectEntity entity) {
        SubjectDto dto = new SubjectDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        // If there are more fields in SubjectDto, set them here

        return dto;
    }
}
