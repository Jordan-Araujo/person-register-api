package com.register.api.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PersonDtoTest")
public class PersonDtoTest {
	
	@Test
	public void personDtoTest() {
	   Long id = 1L;
	   String name = "Joel Silva";
	   String cpf = "412.493.220-06";
	   LocalDate birthdate = LocalDate.of(1991, 10, 15);
	   PersonDto personDto = new PersonDto(id, name, cpf, birthdate);
	   assertEquals(id, personDto.getId());
	   assertEquals(name, personDto.getName());
	   assertEquals(cpf, personDto.getCpf());
	   assertEquals(birthdate, personDto.getBirthdate());
	}
}
