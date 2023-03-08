package com.example.parceldeliveryapp.service;


import com.example.parceldeliveryapp.enums.UserType;
import com.example.parceldeliveryapp.model.Person;
import com.example.parceldeliveryapp.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.parceldeliveryapp.enums.AccessModeType.LOGIN;
import static com.example.parceldeliveryapp.enums.AccessModeType.LOGOUT;


@Service
@RequiredArgsConstructor
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }

    public boolean checkLogin(String email){
        Optional<Person> optionalPerson = personRepository.findByEmail(email);
        return optionalPerson.isPresent();
    }

    public void register(String firstname, String lastname, String email, String position, String password){
        int id = (personRepository.findMaxId() == null) ? 1: personRepository.findMaxId()+1;
        Person person = new Person( UserType.USER.getValue(),firstname, lastname, email, position, password, LOGOUT.getValue());
        personRepository.save(person);
    }

    public Optional<Person> login(String email, String password){
        Optional<Person> optionalUser = personRepository.findByEmailAndPassword(email, password);
        if(optionalUser.isPresent()){
            Person person = optionalUser.get();
            person.setAccessMode(LOGIN.getValue());
            personRepository.save(person);
        }
        return optionalUser;
    }

    public boolean findAuthorizationUser(){
        Optional<Person> optionalUser = personRepository.findByAccessMode(LOGIN.getValue());
        return optionalUser.isPresent();
    }

    public Optional<Person> getAuthUser(){
        return personRepository.findByAccessMode(LOGIN.getValue());
    }

//    //список всех пользователей
//    public List<Person> getAllUsers(){
//        Iterable<Person> users = userRepository.findAll();
//        List<Person> userList = new ArrayList<>();
//        users.forEach(userList::add);
//        return userList;
//    }
//
//    public Optional<Person> login(String login, String password){
//        Optional<Person> optionalUser = userRepository.findByLoginAndPassword(login, password);
//        if(optionalUser.isPresent()){
//            Person person = optionalUser.get();
//            person.setAccessMode(LOGIN.getValue());
//            userRepository.save(person);
//        }
//        return optionalUser;
//    }
//
//    public void register(String name, String login, String password, String mail, String info){
//        int id = (userRepository.findMaxId() == null) ? 1: userRepository.findMaxId()+1;
//        Person user = new Person(id, UserType.USER.getValue(), name, login, password, mail, info, LOGOUT.getValue());
//        userRepository.save(user);
//    }
//
//    public boolean findAuthorizationUser(){
//        Optional<Person> optionalUser = userRepository.findByAccessMode(LOGIN.getValue());
//        return optionalUser.isPresent();
//    }
//
//    public Optional<Person> getAuthUser(){
//        return userRepository.findByAccessMode(LOGIN.getValue());
//    }
//
//    public boolean checkLogin(String login){
//        Optional<Person> optionalUser = userRepository.findByLogin(login);
//        return optionalUser.isPresent();
//    }
//
//    public Optional<Person> findUserById(Integer userId){
//        return userRepository.findById(userId);
//    }
//
//    public void logout(){
//        Optional<Person> optionalUser = getAuthUser();
//        if(optionalUser.isPresent()){
//            Person user = optionalUser.get();
//            user.setAccessMode(LOGOUT.getValue());
//            userRepository.save(user);
//        }
//    }
//
//    public void update(String firstname, String lastname, String email, String password){
//        Optional<Person> optionalUser = getAuthUser();
//        if(optionalUser.isPresent()){
//            Person user = optionalUser.get();
//            user.setFirstName(firstname);
//            user.setLastName(lastname);
//            user.setEmail(email);
//            user.setPassword(password);
//            userRepository.save(user);
//        }
//    }

}
