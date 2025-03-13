package com.school.controlller;

import com.school.model.dto.BasedDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ICrudController <D extends BasedDto<I>,I> {

    @GetMapping
    List<D> getAll();

    @GetMapping("{id}")
    D getById(@PathVariable("id") I id);

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") I id);

    @PostMapping
    D saveOrUpdate(@RequestBody D dto);
}
