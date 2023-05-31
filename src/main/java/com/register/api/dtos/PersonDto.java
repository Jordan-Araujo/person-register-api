package com.register.api.dtos;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

public class PersonDto{
	
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	@CPF
	private String cpf;
	@NotNull
	@Past
	private LocalDate birthdate;
	
	public PersonDto() {
	}

	public PersonDto(Long id, @Valid @NotBlank String name, @NotBlank @CPF String cpf, @NotNull @Past LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthdate = birthdate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}