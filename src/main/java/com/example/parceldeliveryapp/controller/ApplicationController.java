package com.example.parceldeliveryapp.controller;

import com.example.parceldeliveryapp.enums.UserType;
import com.example.parceldeliveryapp.model.Person;
import com.example.parceldeliveryapp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    //private final ProductService productService;
    private final PersonService personService;

    @GetMapping("/")
    public String indexPage(Model model) {
       // setAuth(model);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "login";
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "registration";
    }

   /* @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("person") Person person, Model model) {
        //personRepository.save(sensor);
        personService.save(person);
        return "login";
    }*/

    @PostMapping("/register")
    public String registerPost(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam String confirm,
            Model model) {
        try {
            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || password.isEmpty()) {
                model.addAttribute("error", "Not all fields is filled ");
                return "registration";
            }
            if (!password.equals(confirm)) {
                model.addAttribute("pass", "Passwords isn't compare");
                return "registration";
            }
            if (email.indexOf("@") < 0) {
                model.addAttribute("mail", "Format is false");
                return "registration";
            }
            if (personService.checkLogin(email)) {
                model.addAttribute("login", "This email is already taken");
                return "registration";
            }
            personService.register(firstname, lastname, email, phone, password);
        } catch (Exception e) {
            model.addAttribute("server_error", "Ошибка на сервере");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {
        try {
            if(email.isEmpty() || password.isEmpty()){
                model.addAttribute("params_not_found", "Not all dates is filled");
                return "login";
            }

            Optional<Person> optionalUser = personService.login(email, password);
            if(!optionalUser.isPresent()){
                model.addAttribute("user_not_found", "Person is not found");
                return "login";
            }
            if(optionalUser.get().getTypeId() == UserType.USER.getValue()){
                model.addAttribute("auth", "");
                model.addAttribute("user", optionalUser.get());
                return "index";
            } else {
                model.addAttribute("auth", "");
                model.addAttribute("admin", optionalUser.get());
                return "index";
            }
        } catch(Exception e){
            model.addAttribute("server_error", "Error on server");
        }
        return "login";
    }
}
