package com.school.controlller;

import com.school.model.dto.TimetableOfClassesDto;
import com.school.model.entity.TimetableOfClassesEntity;
import com.school.repository.TimetableOfClassesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("timeofclasses")
public class TimetableOfClassesController implements ICrudController<TimetableOfClassesDto, Long> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimetableOfClassesController.class);

    @Autowired
    private TimetableOfClassesRepository timetableOfClassesRepository;

    @Override
    public List<TimetableOfClassesDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх розкладів занять");

        List<TimetableOfClassesEntity> entityList = timetableOfClassesRepository.findAll();
        List<TimetableOfClassesDto> dtoList = new ArrayList<>(entityList.size());

        for (TimetableOfClassesEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public TimetableOfClassesDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання розкладу занять за ідентифікатором {}", id);
        TimetableOfClassesDto dto = null;
        Optional<TimetableOfClassesEntity> preloadOptional = timetableOfClassesRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - виконання запиту на видалення розкладу занять за ідентифікатором {}", id);
        timetableOfClassesRepository.deleteById(id);
    }

    @Override
    public TimetableOfClassesDto saveOrUpdate(TimetableOfClassesDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення розкладу занять  {} {} {} {} {}",
                dto.getClassroom(), dto.getEndlesson(), dto.getClass(), dto.getStartlesson(), dto.getStudygrade(),
                dto.getSubject(), dto.getTeacher());
        TimetableOfClassesEntity preload;
        if (dto.getId() == null) {
            preload = new TimetableOfClassesEntity();
        } else {
            Optional<TimetableOfClassesEntity> preloadOptional = timetableOfClassesRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(TimetableOfClassesEntity::new);
        }
        preload = timetableOfClassesRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private TimetableOfClassesDto buildDto(TimetableOfClassesEntity entity) {
        TimetableOfClassesDto dto = new TimetableOfClassesDto();
        return dto;
    }
}
