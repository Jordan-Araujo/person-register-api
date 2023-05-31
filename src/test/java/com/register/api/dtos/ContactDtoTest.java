package com.register.api.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ContactDtoTest")
public class ContactDtoTest {
	
	@Test
	public void contactDtoTest() {
	   Long id = 1L;
	   String name = "Lucia Moraes";
	   String phoneNumber = "44 9 9999 4444";
	   String email = "412.493.220-06";
	   ContactDto contactDto = new ContactDto(id, name, phoneNumber, email);
	   assertEquals(id, contactDto.getId());
	   assertEquals(name, contactDto.getName());
	   assertEquals(phoneNumber, contactDto.getPhoneNumber());
	   assertEquals(email, contactDto.getEmail());
	}
}
