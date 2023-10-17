package br.com.api.core.generics.impl;

import br.com.api.core.generics.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static io.micrometer.common.util.StringUtils.isNotBlank;

public abstract class GenericService<I extends IGenericRequestDTO, O extends IGenericResponseDTO, E extends IGenericEntity> implements IGenericService<I, O> {

    protected final IGenericMapper<I, O, E> genericMapper;
    protected final IJpaSpecificationRepository<E, Long> genericRepository;

    protected GenericService(IJpaSpecificationRepository<E, Long> genericRepository, IGenericMapper<I, O, E> genericMapper) {
        this.genericMapper = genericMapper;
        this.genericRepository = genericRepository;
    }

    protected Specification<E> buildDefaultSpecification(List<FilterCriteria> filters) {
        return new AbstractFilterSpecification<>(filters) {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return buildFilterPredicate(root, criteriaBuilder);
            }
        };
    }

    protected Page<E> findByQuery(Pageable pageable, String query) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<O> list(Pageable pageable, String query, List<FilterCriteria> filters) {
        Page<E> list = null;

        if (isNotBlank(query)) {
            list = findByQuery(pageable, query);
        } else {
            list = genericRepository.findAll(Specification.where(buildDefaultSpecification(filters)), pageable);
        }

        return new PageImpl<>(list.stream().map(genericMapper::toDTO).toList(), pageable, list.getTotalElements());
    }

    @Transactional(readOnly = true)
    public Optional<O> get(Long id) {
        Optional<E> entity = genericRepository.findById(id);
        return entity.map(genericMapper::toDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public O create(I requestDTO) {
        this.overridePropertyOnCreateForDTO(requestDTO);
        E entity = genericMapper.toEntity(requestDTO);
        this.overridePropertyOnCreateForEntity(entity);
        genericRepository.saveAndFlush(entity);
        return genericMapper.toDTO(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, I requestDTO) {

        Optional<E> dbOptional = genericRepository.findById(id);

        if (dbOptional.isEmpty()) {
            throw new EntityNotFoundException("Entity not found.");
        }

        E entity = dbOptional.get();
        this.overridePropertyOnUpdateForDTO(requestDTO);
        genericMapper.update(requestDTO, entity);
        this.overridePropertyOnUpdateForEntity(entity);
        genericRepository.saveAndFlush(entity);

    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<E> dbOptional = genericRepository.findById(id);

        if (dbOptional.isEmpty()) {
            throw new EntityNotFoundException("Entity not found.");
        }

        genericRepository.deleteById(id);
    }

    protected void overridePropertyOnCreateForDTO(I requestDTO) {
    }

    protected void overridePropertyOnUpdateForDTO(I requestDTO) {
    }

    protected void overridePropertyOnCreateForEntity(E entity) {
    }

    protected void overridePropertyOnUpdateForEntity(E entity) {
    }

}