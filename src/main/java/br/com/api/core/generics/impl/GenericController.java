package br.com.api.core.generics.impl;

import br.com.api.core.generics.FilterCriteria;
import br.com.api.core.generics.IGenericEntity;
import br.com.api.core.generics.IGenericRequestDTO;
import br.com.api.core.generics.IGenericResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class GenericController<I extends IGenericRequestDTO, O extends IGenericResponseDTO, E extends IGenericEntity> {

    protected final GenericService<I, O, E> service;

    protected GenericController(GenericService<I, O, E> service) {
        this.service = service;
    }

    @GetMapping
    @Hidden
    @Operation(summary = "List pageable")
    public Page<O> list(@PageableDefault Pageable pageable, FilterCriteria filter) {
        return service.list(pageable, filter);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get by id")
    public ResponseEntity<Object> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create")
    public ResponseEntity<O> save(@Valid @RequestBody I requestDTO) {
        return new ResponseEntity<>(service.create(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody I requestDTO) {
        service.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
