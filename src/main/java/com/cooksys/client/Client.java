package com.cooksys.client;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cooksys.address.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	//@JsonIgnore
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@ManyToOne
	private Address address;
	
	@ManyToMany
	private List<Client> relations;
	
	public Client() {}
	
	public Client(String name, String password, Date birthday) {
		this.name=name;
		this.password=password;
		this.birthday=birthday;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public Date getBirthday() {return birthday;}
	public void setBirthday(Date birthday) {this.birthday = birthday;}

	public Address getAddress() {return address;}
	public void setAddress(Address address) {this.address = address;}

	public List<Client> getRelations() {return relations;}
	public void setRelations(List<Client> relations) {this.relations = relations;}
	
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
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
