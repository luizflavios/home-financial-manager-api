package br.com.api.core.generics;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IGenericResponseDTO extends Serializable {
}
