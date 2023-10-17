package br.com.api.core.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IGenericService<I extends IGenericRequestDTO, O extends IGenericResponseDTO> {

    O create(I input);

    void update(Long id, I input);

    void delete(Long id);

    Optional<O> get(Long id);

    Page<O> list(Pageable pageable, String query, List<FilterCriteria> filters);

}
