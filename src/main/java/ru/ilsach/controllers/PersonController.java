package ru.ilsach.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.ilsach.models.Person;
import ru.ilsach.repositories.PersonRepository;

import javax.validation.Valid;

/**
 * Crea
 * */
@Controller
public class PersonController extends WebMvcConfigurerAdapter{
     @Autowired
     private PersonRepository repo;

    @RequestMapping("/login")
    public String logIn ( Model model) {return "login";}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping("/regist")
    public String firstRegist(@Valid Person person, BindingResult bindingResult){return "regist";}

    @PostMapping("/regist")
    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "regist";
        }

        Person m_person = new Person();
        m_person.login = (String)bindingResult.getRawFieldValue("login");
        m_person.firstName=(String)bindingResult.getRawFieldValue("firstName");
        m_person.lastName=(String)bindingResult.getRawFieldValue("lastName");
        m_person.password=(String)bindingResult.getRawFieldValue("password");
        repo.save(m_person);
        model.addAttribute("result","Success, back to the login window");
        return "regist";
    }
}
