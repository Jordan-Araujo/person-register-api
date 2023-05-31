package com.register.api.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PersonModelTest")
public class PersonModelTests {
	
	@Test
	public void personModelTest() {
	   Long id = 1L;
	   String name = "Vinicius Silva";
	   String cpf = "057.677.640-85";
	   LocalDate birthdate = LocalDate.of(1998, 12, 12);
	   
	   List<ContactModel> contacts = new ArrayList<>();
	   contacts.add(new ContactModel());
	   
	   PersonModel personModel = new PersonModel();
	   personModel.setId(id);
	   personModel.setName(name);
	   personModel.setCpf(cpf);
	   personModel.setBirthdate(birthdate);
	   personModel.setContacts(contacts);
	   assertEquals(id, personModel.getId());
	   assertEquals(name, personModel.getName());
	   assertEquals(cpf, personModel.getCpf());
	   assertEquals(birthdate, personModel.getBirthdate());
	   assertTrue(contacts == personModel.getContacts());
	}
}
