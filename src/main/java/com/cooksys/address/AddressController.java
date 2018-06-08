package com.cooksys.address;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.AddressDto;
import com.cooksys.dto.ClientDto;
import com.cooksys.mapper.AddressMapper;

@RestController
@RequestMapping("address")
public class AddressController {

	private AddressService addressService;
	private AddressMapper addressMapper;

	public AddressController(AddressService addressService, AddressMapper addressMapper) {
		super();
		this.addressService = addressService;
		this.addressMapper = addressMapper;
	}

		// GET /address?city=___&state=___
	// Returns a list of all addresses
	// If the optional ‘city’ parameter is provided, the list of addresses must all
	// belong to that particular city
	// If the optional ‘state’ parameter is provided, the list of addresses must all
	// belong to that particular state
	@GetMapping
	public List<AddressDto> getAll(@RequestParam(required = false) String city,
			@RequestParam(required = false) String state) {

		return addressService.getAll(city, state).stream().map(addressMapper::toDto).collect(Collectors.toList());
	}
	
//	GET /address/{id}
//	Returns an address by it’s unique id
	@GetMapping("address/{id}")
	public AddressDto getAddressById(@PathVariable Long id) {
		return addressMapper.toDto(addressService.getAddressById(id));
	}
	
//	POST /address
//	Accepts an Address DTO that contains the following fields
//	Street
//	City
//	State
	@PostMapping
	public Long createAddress(@RequestBody AddressDto addressDto, HttpServletResponse httpResponse) {
		Long id = addressService.createAddress(addressMapper.toEntity(addressDto));
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
//	PUT /address/{id}
//	Replaces the Address entity at the given id with updated information
//	Accepts an Address DTO that contains the following fields
//	Street
//	City
// 	State
	@PutMapping("{id}")
	public void put(@PathVariable Long id, @RequestBody AddressDto addressDto, HttpServletResponse httpeResponse) {
		addressService.replaceAddress(id, addressMapper.toEntity(addressDto));
	}

//	DELETE /address/{id}
//	Deletes the Address at the given id
	@DeleteMapping("{id}")
	public void deleteAddressById(@PathVariable Long id, HttpServletResponse httpResponse) {
		addressService.delete(id);
	}


}
