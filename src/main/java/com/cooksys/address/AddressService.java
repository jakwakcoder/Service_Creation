package com.cooksys.address;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.client.Client;
import com.cooksys.dto.AddressDto;

@Service
public class AddressService {

	private AddressRepository addressRepo;

	public AddressService(AddressRepository addressRepo) {
		super();
		this.addressRepo = addressRepo;
	}
	
	public List<Address> getAll(String city, String state) {

		//we only have city
		if (city != null && state==null) {
			return addressRepo.findByCity(city);

		//we only have state
		} else if (state != null && city==null) {
			return addressRepo.findByState(state);

		//city and state will be null to reach here
		} else {
			return addressRepo.findAll();
		}
	}

	public Address getAddressById(Long id) {
		// TODO Auto-generated method stub
		return addressRepo.findAllById(id);
	}

	public Long createAddress(Address address) {
		address.setId(null);
		return addressRepo.save(address).getId();
	}

	public void replaceAddress(Long id, Address address) {
		address.setId(id);
		addressRepo.save(address);
	}

	@Transactional
	public void delete(Long id) {
		
		for (Client clientWithAddress : addressRepo.findById(id).get().getResidents()) {
			clientWithAddress.setAddress(null);
		}
		addressRepo.deleteById(id);
	}	

}
