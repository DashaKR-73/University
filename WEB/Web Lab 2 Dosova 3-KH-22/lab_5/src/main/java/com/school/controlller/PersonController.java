package com.school.controlller;

import com.school.model.dto.PersonDto;
import com.school.model.entity.PersonEntity;
import com.school.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("persons")

public class PersonController
        implements ICrudController<PersonDto, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDto> getAll() {
        LOGGER.info("In getAll - виконання запиту на отримання усіх персон");

        List<PersonEntity> entityList = personRepository.findAll();
        List<PersonDto> dtoList = new ArrayList<>(entityList.size());

        for (PersonEntity entity : entityList)
            dtoList.add(buildDto(entity));

        return dtoList;
    }

    @Override
    public PersonDto getById(Long id) {
        LOGGER.info("IN getById - виконання запиту на отримання персони за ідентифікатором {}", id);
        PersonDto dto=null;
        Optional<PersonEntity> preloadOptional=personRepository.findById(id);
        if(preloadOptional.isPresent())
            dto=buildDto(preloadOptional.get());
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        LOGGER.info(" IN deleteById - виконання запиту на видалення персони за ідентифікатором {}", id);
        personRepository.deleteById(id);
    }

    @Override
    public PersonDto saveOrUpdate(PersonDto dto) {
        LOGGER.info("IN saveOrUpdate - виконання запиту на збереження чи оновлення персони {} {} {} ",
                dto.getFirstName(),dto.getLastName(),dto.getBirthDay());
        PersonEntity preload;
        if (dto.getId() == null) {
            preload = new PersonEntity();
        } else {
            Optional<PersonEntity> preloadOptional =personRepository.findById(dto.getId());
            preload = preloadOptional.orElseGet(PersonEntity :: new);
        }
        preload.setFirstName(dto.getFirstName());
        preload.setLastName(dto.getLastName());
        preload.setBirthDay(dto.getBirthDay());

        preload = personRepository.save(preload);

        dto.setId(preload.getId());

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
}
