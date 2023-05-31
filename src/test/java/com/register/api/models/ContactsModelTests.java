package com.register.api.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ContactModelTest")
public class ContactsModelTests {
	
	@Test
	public void contactModelTest() {
	   Long id = 1L;
	   String name = "Rodrigo Aparecido";
	   String phoneNumber = "45 9 8899 9889";
	   String email = "rodrigo@email.com";
	   
	   List<ContactModel> contacts = new ArrayList<>();
	   contacts.add(new ContactModel());
	   
	   ContactModel contactModel = new ContactModel();
	   contactModel.setId(id);
	   contactModel.setName(name);
	   contactModel.setPhoneNumber(phoneNumber);
	   contactModel.setEmail(email);
	   assertEquals(id, contactModel.getId());
	   assertEquals(name, contactModel.getName());
	   assertEquals(phoneNumber, contactModel.getPhoneNumber());
	   assertEquals(email, contactModel.getEmail());
	}
}
