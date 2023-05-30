package com.register.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ContactDto {
	
	private Long id;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Phone Number cannot be blank")
	private String phoneNumber;
	@NotBlank
	@Email(regexp = ".+@.+\\..+", message = "Email should be valid")
	private String email;
	
	public ContactDto() {
	}	
	
	public ContactDto(Long id, @NotBlank String name, @NotBlank String phoneNumber, @NotBlank @Email String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}