package br.com.api.core.generics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface IGenericRequestDTO extends Serializable {
}
