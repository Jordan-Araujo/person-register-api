package com.register.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.register.api.dtos.PersonDto;
import com.register.api.models.PersonModel;
import com.register.api.services.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public ResponseEntity<List<PersonModel>> findAllPersonData(){
		return ResponseEntity.status(HttpStatus.OK).body(personService.findAllData());
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<Page<PersonModel>> search(
			@RequestParam("searchTerm") String term,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size){
		return ResponseEntity.status(HttpStatus.OK).body(personService.search(term, page, size));
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Object> findPersonDataById(@PathVariable Long id){
		Optional<PersonModel> personModel = personService.findById(id);
		if(!personModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(personModel.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> insertNewPerson(@RequestBody @Valid PersonDto personDto){
		PersonModel personModel = new PersonModel();
		BeanUtils.copyProperties(personDto, personModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));
	}
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<Object> deletePersonDataById(@PathVariable Long id){
		Optional<PersonModel> personModel = personService.findById(id);
		if(!personModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
		}
		personService.delete(personModel.get());
		return ResponseEntity.status(HttpStatus.OK).body("Person data deleted successfully");
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Object> updatePersonDataById(@PathVariable Long id, @RequestBody @Valid PersonDto personDto){
		Optional<PersonModel> personModel = personService.findById(id);
		if(!personModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
		}
		if(personModel.get().getId() != personDto.getId()) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("You are not allowed to modify the id");
		}
		PersonModel model = personModel.get();
		model.setName(personDto.getName());
		model.setCpf(personDto.getCpf());
		model.setBirthdate(personDto.getBirthdate());
		return ResponseEntity.status(HttpStatus.OK).body(personService.save(model));
	}
	
	
}