package com.register.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.api.models.ContactModel;
import com.register.api.repositories.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository repository;
	
	public ContactModel save(ContactModel obj) {
		return repository.save(obj);
	}
	
	public List<ContactModel> findAll(){
		return repository.findAll();
	}
	
	public Optional<ContactModel> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public void delete(ContactModel obj) {
		repository.delete(obj);
	}
}
