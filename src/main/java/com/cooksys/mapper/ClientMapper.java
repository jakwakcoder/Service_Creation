package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.client.Client;
import com.cooksys.dto.ClientDto;

@Mapper(componentModel = "spring")
public interface ClientMapper {

		ClientDto toDto(Client client);

		Client toEntity(ClientDto clientDto);
}
