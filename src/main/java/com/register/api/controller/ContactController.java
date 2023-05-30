package com.register.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.register.api.dtos.ContactDto;
import com.register.api.models.ContactModel;
import com.register.api.services.ContactService;

@RestController
@RequestMapping(value="/contact")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public ResponseEntity<List<ContactModel>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll());
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		Optional<ContactModel> contactModel = contactService.findById(id);
		if(!contactModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contactModel.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> insertNewContact(@RequestBody @Valid ContactDto contactDto){
		ContactModel contactModel = new ContactModel();
		BeanUtils.copyProperties(contactDto, contactModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactModel));
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Object> updateContactDataById(@PathVariable Long id, @RequestBody @Valid ContactDto contactDto){
		Optional<ContactModel> contactModel = contactService.findById(id);
		if(!contactModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
		}
		if(contactModel.get().getId() != contactDto.getId()) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("You are not allowed to modify the id");
		}
		ContactModel model = contactModel.get();
		model.setName(contactDto.getName());
		model.setPhoneNumber(contactDto.getPhoneNumber());
		model.setEmail(contactDto.getEmail());
		contactService.save(model);
		return ResponseEntity.status(HttpStatus.OK).body("Contact data updated successfully");
	}
}