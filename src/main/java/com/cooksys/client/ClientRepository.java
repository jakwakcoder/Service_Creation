package com.cooksys.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.address.Address;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Address findByAddressId(Long Id);

}
