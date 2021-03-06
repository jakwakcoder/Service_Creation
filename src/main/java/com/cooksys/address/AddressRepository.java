package com.cooksys.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByCity(String city);

	List<Address> findByState(String state);

	Address findAllById(Long id);

}
