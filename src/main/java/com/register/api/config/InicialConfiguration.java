package com.register.api.config;

import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.register.api.dtos.ContactDto;
import com.register.api.dtos.PersonDto;
import com.register.api.models.ContactModel;
import com.register.api.models.PersonModel;
import com.register.api.repositories.ContactRepository;
import com.register.api.repositories.PersonRepository;

@Configuration
public class InicialConfiguration implements CommandLineRunner{
	
	@Autowired
	private PersonRepository personRepo;
	@Autowired 
	private ContactRepository contactRepo;

	protected PersonModel PersonDtoToModel(@Valid PersonDto dto) {
		PersonModel model = new PersonModel();
		model.setName(dto.getName());
		model.setCpf(dto.getCpf());
		model.setBirthdate(dto.getBirthdate());
		return model;
	}
	
	protected ContactModel ContactDtoToModel(@Valid ContactDto dto) {
		ContactModel model = new ContactModel();
		model.setName(dto.getName());
		model.setPhoneNumber(dto.getPhoneNumber());
		model.setEmail(dto.getEmail());
		return model;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		personRepo.deleteAll();
		contactRepo.deleteAll();
		
		PersonDto user01 = new PersonDto(null, "Joao Alves", "641.030.280-09", LocalDate.of(2005, 03, 20));
		PersonDto user02 = new PersonDto(null, "Maria Silva", "425.950.800-83", LocalDate.of(2006, 06, 21));
		PersonDto user03 = new PersonDto(null, "Pedro Araujo", "066.428.580-50", LocalDate.of(2007, 07, 22));
		PersonDto user04 = new PersonDto(null, "Roberto Alves", "250.578.350-09", LocalDate.of(1995, 02, 20));
		PersonDto user05 = new PersonDto(null, "Matheus Silva", "627.288.470-29", LocalDate.of(1992, 11, 21));
		PersonDto user06 = new PersonDto(null, "Gustavo Araujo", "019.037.970-71", LocalDate.of(1990, 05, 22));
		
		PersonModel personModel01 = PersonDtoToModel(user01);
		PersonModel personModel02 = PersonDtoToModel(user02);
		PersonModel personModel03 = PersonDtoToModel(user03);
		PersonModel personModel04 = PersonDtoToModel(user04);
		PersonModel personModel05 = PersonDtoToModel(user05);
		PersonModel personModel06 = PersonDtoToModel(user06);		
		
		ContactDto contact01 = new ContactDto(null, "Gustavo Moraes", "44 99999 9999", "gustavo@email.com");
		ContactDto contact02 = new ContactDto(null, "Jose Rodrigues", "43 98888 8888", "jose@email.com");
		ContactDto contact03 = new ContactDto(null, "Vinicius Perdomo", "42 97777 7777", "vinicius@email.com");
		ContactDto contact04 = new ContactDto(null, "Maria Pimenta", "48 96666 6666", "maria@email.com");
		ContactDto contact05 = new ContactDto(null, "Claudia Alvez", "25 95555 5555", "claudia@email.com");
		ContactDto contact06 = new ContactDto(null, "Julia Caetano", "11 94444 4444", "julia@email.com");
		ContactDto contact07 = new ContactDto(null, "Vitoria Azevedo", "52 93333 3333", "vitoria@email.com");
		ContactDto contact08 = new ContactDto(null, "Marcelo Andrade", "66 92222 2222", "marcelo@email.com");
		ContactDto contact09 = new ContactDto(null, "Claudio Peroni", "32 91111 1111", "claudio@email.com");
		ContactDto contact10 = new ContactDto(null, "Rodrigo Safra", "31 93030 3030", "rodrigo@email.com");
		ContactDto contact11 = new ContactDto(null, "Giovana Campana", "52 92020 2020", "giovana@email.com");
		ContactDto contact12 = new ContactDto(null, "Henrique Araujo", "40 91010 1010", "henrique@email.com");
		
		ContactModel contactModel01 = ContactDtoToModel(contact01);
		ContactModel contactModel02 = ContactDtoToModel(contact02);
		ContactModel contactModel03 = ContactDtoToModel(contact03);
		ContactModel contactModel04 = ContactDtoToModel(contact04);
		ContactModel contactModel05 = ContactDtoToModel(contact05);
		ContactModel contactModel06 = ContactDtoToModel(contact06);
		ContactModel contactModel07 = ContactDtoToModel(contact07);
		ContactModel contactModel08 = ContactDtoToModel(contact08);
		ContactModel contactModel09 = ContactDtoToModel(contact09);
		ContactModel contactModel10 = ContactDtoToModel(contact10);
		ContactModel contactModel11 = ContactDtoToModel(contact11);
		ContactModel contactModel12 = ContactDtoToModel(contact12);
		
		personRepo.saveAll(Arrays.asList(personModel01, personModel02, personModel03, personModel04, personModel05, personModel06));
		contactRepo.saveAll(Arrays.asList(contactModel01, contactModel02, contactModel03, contactModel04, contactModel05, contactModel06, contactModel07, 
		contactModel08, contactModel09, contactModel10, contactModel11, contactModel12));

		personModel01.getContacts().addAll(Arrays.asList(contactModel12, contactModel04));
		personModel02.getContacts().addAll(Arrays.asList(contactModel03, contactModel06));
		personModel03.getContacts().addAll(Arrays.asList(contactModel05, contactModel09));
		personModel04.getContacts().addAll(Arrays.asList(contactModel02, contactModel11));
		personModel05.getContacts().addAll(Arrays.asList(contactModel08, contactModel10));
		personModel06.getContacts().addAll(Arrays.asList(contactModel01, contactModel07));

		personRepo.saveAll(Arrays.asList(personModel01, personModel02, personModel03, personModel04, personModel05, personModel06));
	}
}