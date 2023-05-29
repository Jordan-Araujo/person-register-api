package com.register.api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_person")
public class PersonModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(nullable = false)
	private String Name;
	@Column(nullable = false, unique = true)
	private String Cpf;
	@Column(nullable = false)
	private LocalDate Birthdate;
	
	@OneToMany
	private List<ContactModel> contacts;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public LocalDate getBirthdate() {
		return Birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		Birthdate = birthdate;
	}

	public List<ContactModel> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactModel> contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonModel other = (PersonModel) obj;
		return Objects.equals(Cpf, other.Cpf);
	}
}