package com.cooksys.client;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.address.Address;
import com.cooksys.dto.AddressDto;
import com.cooksys.dto.ClientDto;
import com.cooksys.mapper.AddressMapper;
import com.cooksys.mapper.ClientMapper;


@RestController
@RequestMapping("client")
public class ClientController {
	
	private ClientService clientService;
	private ClientMapper clientMapper;
	private AddressMapper addressMapper;
	public ClientController(ClientService clientService, ClientMapper clientMapper, AddressMapper addressMapper) {
		super();
		this.clientService = clientService;
		this.clientMapper = clientMapper;
		this.addressMapper=addressMapper;
	}

//	GET /user
	@GetMapping	
	public List<ClientDto> getAll(){
			return clientService.getAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
	}
	
//	GET /user/{id}
	@GetMapping("user/{id}")
	public ClientDto getClientById(@PathVariable Long id) {
		return clientMapper.toDto(clientService.getClientById(id));
	}
	
//	GET /user/{id}/address
	@GetMapping("{id}/address/")
	public AddressDto getAddressByClientById(@PathVariable Long id) {
		return addressMapper.toDto(clientService.getAddressClientById(id));
	}
	
//	GET /user/{id}/relations
//	Returns a list of User DTO  this User is related to
	@GetMapping("{id}/relations")
	public List<ClientDto> getRelationsByClientId(@PathVariable Long id){
		return clientService.getRelationsByClientId(id).stream().map(clientMapper::toDto).collect(Collectors.toList());
	}
	
//	GET /address/{id}/residents
//	Returns a list of User DTOs associated with this address
	@GetMapping("user/{id}/address/")
	public List<ClientDto> getClientsByAddressId(@PathVariable Long id) {
		return clientService.getClientsByAddressId(id).stream().map(clientMapper::toDto).collect(Collectors.toList());
	}
	
//	POST /user
//	Accepts a User DTO that contains the following fields
//	Name
//	Password
//	Birthday
	@PostMapping
	public Long createClient(@RequestBody ClientDto clientDto, HttpServletResponse httpResponse) {
		Long id = clientService.createClient(clientMapper.toEntity(clientDto));
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
//	POST /user/{id}/relations/{relationId}
//	Adds a relation to another user
//	{relationId} must point to a valid User
	@PostMapping("{clientId}/relations/{relationId}")
	@Transactional
	public void addRelation(@PathVariable (name = "clientId") Client client, @PathVariable(name = "relationId") Client relationClient) {
		client.getRelations().add(relationClient);
		relationClient.getRelations().add(client);
		}
	
//	POST /user/{id}/address/{addressId}
//	Sets the address of the referenced User

	@PostMapping("{clientId}/address/{addressId}")
	public void addAddress(@PathVariable(name = "clientId") ClientDto clientDto, @PathVariable(name = "addressId") AddressDto addressDto) {
		clientService.addAddress(clientMapper.toEntity(clientDto), addressMapper.toEntity(addressDto));
	}

//	PUT /user/{id}
//	Replaces the User entity at the given id with updated information
//	Accepts a User DTO that contains the following fields
//	Name
//	Password
//	Birthday
//	Administrator
	
	@PutMapping("{id}")
	public void putClientId(@PathVariable Long id, @RequestBody ClientDto clientDto, HttpServletResponse httpReponse) {
		clientService.putClientId(id, clientMapper.toEntity(clientDto));
	}
	
//	DELETE /user/{id}
//	Deletes the User at the given id
	@DeleteMapping("{id}")
	public void deleteClientAtId(@PathVariable Long id, HttpServletResponse httpResponse) {
		clientService.deleteClientById(id);
	}
	
}
