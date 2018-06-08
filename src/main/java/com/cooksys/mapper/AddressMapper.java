package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.address.Address;
import com.cooksys.dto.AddressDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDto toDto(Address address);

	Address toEntity(AddressDto addressDto);
}
