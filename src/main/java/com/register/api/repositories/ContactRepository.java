package com.register.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.register.api.models.ContactModel;

@Repository
public interface ContactRepository extends JpaRepository<ContactModel, Long>{

}
