package com.register.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.register.api.models.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long>{
	
	@Query("FROM PersonModel p " + "WHERE LOWER(p.name) like %:searchTerm% " + "OR LOWER(p.cpf) like %:searchTerm%")
	Page<PersonModel> search(@Param("searchTerm") String searchTerm, Pageable pageable);
}