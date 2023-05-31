package com.register.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.register.api.AplicationConfigTest;
import com.register.api.dtos.PersonDto;
import com.register.api.models.ContactModel;
import com.register.api.models.PersonModel;
import com.register.api.services.PersonService;

@DisplayName("PersonControllerTest")
public class PersonControllerTest extends AplicationConfigTest{
	
	@MockBean
	private PersonService personService;
	
	@Autowired
	PersonController personController;
	
	@Test
	@DisplayName("Must find all person data")
	public void findAllPersonDataTest() {
	   PersonModel person1 = new PersonModel();
	   PersonModel person2 = new PersonModel();
	   List<PersonModel> expected = new ArrayList<>();
	   expected.add(person1);
	   expected.add(person2);
	   Mockito.when(personService.findAllData()).thenReturn(expected);
	   ResponseEntity<List<PersonModel>> responseEntity = personController.findAllPersonData();
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(expected, responseEntity.getBody());
	   Mockito.verify(personService, Mockito.times(1)).findAllData();
	}
	
	@Test
	@DisplayName("Must find data based on requirements/filters")
	public void searchTest() {
	   String term = "John";
	   int page = 0;
	   int size = 10;
	   Page<PersonModel> personPage = new PageImpl<>(Arrays.asList(new PersonModel()), PageRequest.of(page, size), 1);
	   Mockito.when(personService.search(term, page, size)).thenReturn(personPage);
	   ResponseEntity<Page<PersonModel>> responseEntity = personController.search(term, page, size);
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(personPage, responseEntity.getBody());
	}
	
	@Test
	@DisplayName("Must find a person data by id")
	public void findPersonDataByIdTest() {
	   Long id = 1L;
	   PersonModel person = new PersonModel();
	   Mockito.when(personService.findById(id)).thenReturn(Optional.of(person));
	   ResponseEntity<Object> responseEntity = personController.findPersonDataById(id);
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(person, responseEntity.getBody());
	   Mockito.verify(personService, Mockito.times(1)).findById(id);
	}
	
	@Test
	@DisplayName("Must insert a person data by Dto")
	public void insertNewPersonTest() {
	   PersonModel personModel = new PersonModel();
	   List<ContactModel> list= new ArrayList<>();
	   personModel.setName("John Doe");
	   personModel.setCpf("123.456.789-00");
	   personModel.setBirthdate(LocalDate.of(1990, 10, 15));
	   personModel.setContacts(list);	   
	   Mockito.when(personService.save(personModel)).thenReturn(personModel);	   
	   ResponseEntity<Object> responseEntity = personController.insertNewPerson(personModel);	   
	   assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	   assertEquals(personModel, responseEntity.getBody()); 
	   Mockito.verify(personService, Mockito.times(1)).save(personModel);
	}
	
	@Test
	@DisplayName("Must delete a person data by id")
	public void deletePersonDataByIdTest() {
		Long id = 1L;
		   PersonModel person = new PersonModel();
		   Mockito.when(personService.findById(id)).thenReturn(Optional.of(person));
		   ResponseEntity<Object> responseEntity = personController.deletePersonDataById(id);
		   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		   assertEquals("Person data deleted successfully", responseEntity.getBody()); 
		   Mockito.verify(personService, Mockito.times(1)).delete(person);
	}
	
	@Test
	@DisplayName("Must update a person data by id")
	public void updatePersonDataByIdTest() {
	   Long id = 1L;
	   List<ContactModel> list= new ArrayList<>();
	   PersonModel person = new PersonModel();
	   person.setId(id);
	   PersonModel personModel = new PersonModel();
	   personModel.setId(id);
	   personModel.setName("John Doe");
	   personModel.setCpf("123.456.789-00");
	   personModel.setBirthdate(LocalDate.of(1990, 10, 15));
	   personModel.setContacts(list);
	   Mockito.when(personService.findById(id)).thenReturn(Optional.of(person));
	   Mockito.when(personService.save(person)).thenReturn(person);
	   ResponseEntity<Object> responseEntity = personController.updatePersonDataById(id, personModel);
	   assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	   assertEquals(person, responseEntity.getBody()); 
	   Mockito.verify(personService, Mockito.times(1)).save(person);
	}
}
