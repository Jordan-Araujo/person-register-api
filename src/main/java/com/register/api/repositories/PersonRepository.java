package com.register.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.api.models.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long>{
}