package dev.ufuk.bakan.people.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ufuk.bakan.entities.Person;
import dev.ufuk.bakan.people.dto.SignupPersonDTO;
import dev.ufuk.bakan.repositories.PersonRepository;

@Service
public class PeopleService {
    @Autowired
    private PersonRepository personRepository;

    public Person getPersonByCustomerNo(long customerNo){
        return personRepository.retrievePersonByCustomerNo(customerNo);
    }

    public Person getPersonById(String id){
        return personRepository.retrievePersonById(id);
    }

    public void addPerson(SignupPersonDTO signupPersonDTO){
        personRepository.save(new Person(signupPersonDTO));
    }

    public List<Person> getAllPeople(){
        return personRepository.retrieveAll();
    }
}
