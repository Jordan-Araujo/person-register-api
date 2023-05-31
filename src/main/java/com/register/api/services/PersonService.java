package com.register.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.register.api.models.PersonModel;
import com.register.api.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public Page<PersonModel> search(String searchTerm, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return repository.search(searchTerm.toLowerCase(), pageRequest);
    }
	
	public PersonModel save(PersonModel obj) {
		return repository.save(obj);
	}

	public Optional<PersonModel> findById(Long id) {
		return repository.findById(id);
	}
	
	
	public List<PersonModel> findAllData(){
		return repository.findAll();
	}
	
	public Page<PersonModel> findAll(){
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
		return new PageImpl<>(repository.findAll(), pageRequest, size);
	}

	@Transactional
	public void delete(PersonModel obj) {
		repository.delete(obj);
	}
}