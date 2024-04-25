package com.example.atividade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import com.example.atividade.exceptions.CreatePersonException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.example.atividade.models.Person;
import com.example.atividade.repository.PersonRepository;
import com.example.atividade.services.PersonService;

// Isso dai a gente vai estender as funcionalidades do Mockito
@ExtendWith(MockitoExtension.class) 
public class ServiceTests {
    
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    Person person;
    @BeforeEach
    public void personSetup() {
        person = new Person(
            "Aurora",
            "Kruschewsky",
            "Aurora@email.com.br",
            "Uberlândia - Minas Gerais - Brasil",
            "aaaaa");
    }

    @Test
    @DisplayName("Teste mockado para criar pessoa")
    void createPerson() {
        when(personRepository.findByEmail(person.getEmail())).thenReturn(Optional.empty());
        when(personRepository.save(person)).thenReturn(person);

        Person savedPerson = personService.create(person);

        assertNotNull(savedPerson);
        assertEquals(person.getFirstName(), savedPerson.getFirstName());
        assertEquals(person.getLastName(), savedPerson.getLastName());
        assertEquals(person.getEmail(), savedPerson.getEmail());
        assertEquals(person.getAddress(), savedPerson.getAddress());
    }

    @Test
    @DisplayName("Teste mockado para não salvar pessoa")
    void CreatePersonException() {

        when(personRepository.findByEmail(person.getEmail())).thenReturn(Optional.of(person));
        assertThrows(CreatePersonException.class, ()->{
            personService.create(person);
        });


        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    @DisplayName("Teste mockado para retornar lista de pessoas")
    void testarRetornoDeListaDePessosa(){
        when(personRepository.findAll()).thenReturn(List.of(person));
        List<Person> listaPessoas = personService.findAll();

        assertNotNull(listaPessoas);
        assertEquals(1, listaPessoas.size());
    }

    @Test
    @DisplayName("Teste mockado para achar o id")
    void FindbyIdTest() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        Person pessoaSalva = personService.findById(1L);
        assertNotNull(pessoaSalva);
        assertEquals(person.getFirstName(), pessoaSalva.getFirstName());
    }
}
