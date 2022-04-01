package dev.ufuk.bakan.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.ufuk.bakan.entities.Person;

@Component
public class PersonRepository {
    private List<Person> personList = new ArrayList<>();

    public Person retrievePersonByCustomerNo(long customerNo){
        return personList.stream().filter(p -> p.getCustomerNo() == customerNo).findFirst().orElseGet(()-> null);
    }

    public Person retrievePersonById(String id){
        return personList.stream().filter(p -> p.getId() == id).findFirst().orElseGet(()-> null);
    }

    public void save(Person person){
        personList.add(person);
    }

    public List<Person> retrieveAll(){
        return personList;
    }

}
