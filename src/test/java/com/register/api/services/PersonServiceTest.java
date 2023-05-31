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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.register.api.AplicationConfigTest;
import com.register.api.models.PersonModel;
import com.register.api.repositories.PersonRepository;

@DisplayName("PersonServiceTest")
public class PersonServiceTest extends AplicationConfigTest {

	@MockBean
	private PersonRepository repository;

	@Autowired
	PersonService personService;
	
	@Test
	@DisplayName("Must search from person repository query")
	public void searchTest() {
	   String searchTerm = "john doe";
	   int page = 0;
	   int size = 10;
	   Page<PersonModel> expectedPage = new PageImpl<>(new ArrayList<>());
	   PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
	   Mockito.when(repository.search(searchTerm.toLowerCase(), pageRequest)).thenReturn(expectedPage);
	   Page<PersonModel> pageResult = personService.search(searchTerm, page, size);
	   assertEquals(expectedPage,pageResult);
	}
	
	@Test
	@DisplayName("Must save person")
	public void savePersonTest() {
		Long id = 1L;
		PersonModel person = new PersonModel();
		person.setId(id);
		PersonModel expected = person;
		Mockito.when(repository.save(person)).thenReturn(expected);
		PersonModel result = personService.save(person);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Must find person by id")
	public void findPersonByIdTest() {
		Long id = 1L;
		PersonModel person = new PersonModel();
		person.setId(id);
		Optional<PersonModel> expected = Optional.of(person);
		Mockito.when(repository.findById(id)).thenReturn(expected);
		Optional<PersonModel> result = personService.findById(id);
		assertEquals(expected, result);
	}

	@Test
	@DisplayName("Must find all person")
	public void findAllPersonDataTest() {
		Long id = 1L;
		PersonModel person = new PersonModel();
		person.setId(id);
		List<PersonModel> list = new ArrayList<>();
		list.add(person);
		Mockito.when(repository.findAll()).thenReturn(list);
		List<PersonModel> result = personService.findAllData();
		assertEquals(list, result);
	}
	
	@Test
	@DisplayName("Must find all person with pageable")
	public void findAllPersonPageableTest() {
	   int page = 0;
	   int size = 10;
	   PageRequest expected = PageRequest.of(page, size, Sort.Direction.ASC, "name");
	   List<PersonModel> expectedList = new ArrayList<>();
	   PersonModel person1 = new PersonModel();
	   PersonModel person2 = new PersonModel();
	   expectedList.add(person1);
	   expectedList.add(person2);
	   PageImpl<PersonModel> expectedPage = new PageImpl<>(expectedList, expected, size);
	   Mockito.when(repository.findAll()).thenReturn(expectedList);
	   Page<PersonModel> resultPage = personService.findAll();
	   assertEquals(expectedPage,resultPage);
	}

	@Test
	@DisplayName("Must delete person")
	public void deletePersonTest() {
	   PersonModel person = new PersonModel();
	   Mockito.doNothing().when(repository).delete(person);
	   personService.delete(person);
	   Mockito.verify(repository, Mockito.times(1)).delete(person);
	}
}