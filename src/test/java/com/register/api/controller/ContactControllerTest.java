package com.register.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.register.api.AplicationConfigTest;
import com.register.api.dtos.ContactDto;
import com.register.api.models.ContactModel;
import com.register.api.services.ContactService;

@DisplayName("ContactControllerTest")
public class ContactControllerTest extends AplicationConfigTest{
	
	@MockBean
	private ContactService contactService;
	
	@Autowired
	ContactController contactController;
	
	@Test
	@DisplayName("Must find all contact data")
	public void findAllContactDataTest() {
	   ContactModel contact1 = new ContactModel();
	   ContactModel contact2 = new ContactModel();
	   List<ContactModel> expected = new ArrayList<>();
	   expected.add(contact1);
	   expected.add(contact2);
	   Mockito.when(contactService.findAll()).thenReturn(expected);
	   ResponseEntity<List<ContactModel>> responseEntity = contactController.findAll();
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(expected, responseEntity.getBody());
	   Mockito.verify(contactService, Mockito.times(1)).findAll();
	}
	
	@Test
	@DisplayName("Must find a contact data by id")
	public void findContactDataByIdTest() {
	   Long id = 1L;
	   ContactModel contact = new ContactModel();
	   Mockito.when(contactService.findById(id)).thenReturn(Optional.of(contact));
	   ResponseEntity<Object> responseEntity = contactController.findById(id);
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(contact, responseEntity.getBody());
	   Mockito.verify(contactService, Mockito.times(1)).findById(id);
	}
	
	@Test
	@DisplayName("Must insert a contact data by Dto")
	public void insertNewContactTest() {
	   ContactDto contactDto = new ContactDto();
	   contactDto.setName("Jhonny Pereira");
	   contactDto.setPhoneNumber("44 9 9876 6541");
	   contactDto.setEmail("jhonny@email.com");
	   ContactModel contactModel = new ContactModel();
	   BeanUtils.copyProperties(contactDto, contactModel);	   
	   Mockito.when(contactService.save(contactModel)).thenReturn(contactModel);	   
	   ResponseEntity<Object> responseEntity = contactController.insertNewContact(contactDto);	   
	   assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	   assertEquals(contactModel, responseEntity.getBody()); 
	   Mockito.verify(contactService, Mockito.times(1)).save(contactModel);
	}
	
	@Test
	@DisplayName("Must update a contact data by id")
	public void updateContactDataByIdTest() {
	   Long id = 1L;
	   ContactModel contact = new ContactModel();
	   contact.setId(id);
	   ContactDto contactDto = new ContactDto();
	   contactDto.setId(id);
	   contactDto.setName("John Doe");
	   contactDto.setPhoneNumber("44 9 9876 6541");
	   contactDto.setEmail("jhonny@email.com");
	   Mockito.when(contactService.findById(id)).thenReturn(Optional.of(contact));
	   Mockito.when(contactService.save(contact)).thenReturn(contact);
	   ResponseEntity<Object> responseEntity = contactController.updateContactDataById(id, contactDto);
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(contact, responseEntity.getBody()); 
	   Mockito.verify(contactService, Mockito.times(1)).save(contact);
	}
}
