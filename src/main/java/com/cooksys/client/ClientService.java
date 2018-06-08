package com.cooksys.client;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cooksys.address.Address;
import com.cooksys.dto.ClientDto;
import com.cooksys.mapper.ClientMapper;

@Service
public class ClientService {

	private ClientRepository clientRepo;

	public ClientService(ClientRepository clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}

	public List<Client> getAll() {
		return clientRepo.findAll();
	}

	public Client getClientById(Long id) {
		return clientRepo.getOne(id);
	}

	public Address getAddressClientById(Long id) {
		return clientRepo.findByAddressId(id);
	}

	public List<Client> getRelationsByClientId(Long id) {
		return clientRepo.findById(id).get().getRelations();
	}

	public List<Client> getClientsByAddressId(Long id) {
		return clientRepo.findById(id).get().getRelations();
	}

	public Long createClient(Client client) {
		client.setId(null);
		return clientRepo.save(client).getId();
	}

	@Transactional
	public void addAddress(Client client, Address address) {
		client.setAddress(address);
		address.getResidents().add(client);
	}

	public void putClientId(Long id, Client client) {
		client.setId(id);
		clientRepo.save(client);
	}
	
	@Transactional
	public void deleteClientById(Long id) {
			//for each client by given id, get their clientRelations
			//find the clients in their relations by their and remove them
		for (Client tempClient : clientRepo.findById(id).get().getRelations()) {
			tempClient.getRelations().remove(clientRepo.findById(id).get());
		}
		clientRepo.deleteById(id);
	}

}
