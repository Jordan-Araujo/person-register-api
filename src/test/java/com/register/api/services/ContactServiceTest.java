package com.register.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.register.api.AplicationConfigTest;
import com.register.api.models.ContactModel;
import com.register.api.repositories.ContactRepository;

@DisplayName("ContactServiceTest")
public class ContactServiceTest extends AplicationConfigTest {

	@MockBean
	private ContactRepository repository;

	@Autowired
	ContactService contactService;
	
	@Test
	@DisplayName("Must save contact")
	public void mustSaveContactTest() {
		Long id = 1L;
		ContactModel contact = new ContactModel();
		contact.setId(id);
		ContactModel expected = contact;
		Mockito.when(repository.save(contact)).thenReturn(expected);
		ContactModel result = contactService.save(contact);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Must find contact by id")
	public void findContactByIdTest() {
		Long id = 1L;
		ContactModel contact = new ContactModel();
		contact.setId(id);
		Optional<ContactModel> expected = Optional.of(contact);
		Mockito.when(repository.findById(id)).thenReturn(expected);
		Optional<ContactModel> result = contactService.findById(id);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Must find all contact")
	public void mustFindAllContactDataTest() {
		Long id = 1L;
		ContactModel contact = new ContactModel();
		contact.setId(id);
		List<ContactModel> list = new ArrayList<>();
		list.add(contact);
		Mockito.when(repository.findAll()).thenReturn(list);
		List<ContactModel> result = contactService.findAll();
		assertEquals(list, result);
	}

	@Test
	@DisplayName("Must delete contact")
	public void mustDeleteContactTest() {
	   ContactModel contact = new ContactModel();
	   Mockito.doNothing().when(repository).delete(contact);
	   contactService.delete(contact);
	   Mockito.verify(repository, Mockito.times(1)).delete(contact);
	}
}