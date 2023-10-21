package br.com.api.models.mapper;

import br.com.api.core.generics.IGenericMapper;
import br.com.api.models.dto.transaction.TransactionRequestDTO;
import br.com.api.models.dto.transaction.TransactionResponseDTO;
import br.com.api.models.entities.Transaction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper extends IGenericMapper<TransactionRequestDTO, TransactionResponseDTO, Transaction> { }
