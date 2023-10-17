package br.com.api.core.generics;

import org.mapstruct.MappingTarget;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericMapper<I extends IGenericRequestDTO, O extends IGenericResponseDTO, E extends IGenericEntity> {

    E toEntity(I input);

    O toDTO(E entity);

    void update(I input, @MappingTarget E entity);

}
