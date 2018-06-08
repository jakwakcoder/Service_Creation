package com.cooksys.dto;

import java.util.List;

import com.cooksys.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressDto {
	private Long id;
	
	private String street;
	private String city;
	private String state;
	
	@JsonIgnore
	private List<Client> residents;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getStreet() {return street;}
	public void setStreet(String street) {this.street = street;}

	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	public String getState() {return state;}
	public void setState(String state) {this.state = state;}

	public List<Client> getResidents() {return residents;}
	public void setResidents(List<Client> residents) {this.residents = residents;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDto other = (AddressDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
