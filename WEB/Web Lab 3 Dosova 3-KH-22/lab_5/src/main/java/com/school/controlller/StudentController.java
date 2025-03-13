package com.school.controlller;

import com.school.model.dto.PersonDto;
import com.school.model.dto.StudentDto;
import com.school.model.entity.PersonEntity;
import com.school.model.entity.StudentEntity;
import com.school.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController implements ICrudController<StudentDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAll() {
        LOGGER.info("IN getAll - виконання запиту на отримання всіх школярів");

        List<StudentEntity> entityList = studentRepository.findAll();
        List<StudentDto> dtoList = new ArrayList<>(entityList.size());

        for (StudentEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public StudentDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання школярів за ідентифікатором {}", id);
        StudentDto dto = null;
        Optional<StudentEntity> preloadOptional = studentRepository.findById(id);
        if (preloadOptional.isPresent())
            dto = buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info("IN deleteById - вкрнання запиту на видалення школяра за ідентифікатором {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto saveOrUpdate(StudentDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення школяра {} {}",
                dto.getPersonDto(), dto.getGrade());
        StudentEntity preload;
        if (dto.getId() == null) {
            preload = new StudentEntity();
        } else {
            Optional<StudentEntity> preloadOptional = studentRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(StudentEntity::new);
        }
        preload.setPerson(buildEntity(dto.getPersonDto()));
        preload.setGrade(preload.getGrade());

        preload = studentRepository.save(preload);

        dto.setId(preload.getId());

        return dto;
    }

    private StudentDto buildDto(StudentEntity entity) {
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setPersonDto(buildDto(entity.getPerson()));
        dto.setGrade(dto.getGrade());

        return dto;
    }

    private PersonDto buildDto(PersonEntity entity) {
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthDay(entity.getBirthDay());

        return dto;
    }

    private PersonEntity buildEntity(PersonDto dto) {
        PersonEntity entity = new PersonEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDay(dto.getBirthDay());

        return entity;
    }
}
