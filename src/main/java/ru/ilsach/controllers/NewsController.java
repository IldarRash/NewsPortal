package ru.ilsach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.ilsach.models.News;
import ru.ilsach.repositories.NewsRepository;
import ru.ilsach.services.NewsService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by ilsac on 30.04.2017.
 */
@Controller
public class NewsController extends WebMvcConfigurerAdapter {
    @Autowired
    private NewsService service;




    @RequestMapping("/")
    public String homePage(@Valid News object, BindingResult bindingResult,Model model) {
        model.addAttribute("object",new News());
        model.addAttribute("repo", service.findAllNews());
        return "index";
    }

    @RequestMapping("/index")
    public String firstEnter(@Valid News object, BindingResult bindingResult,Model model){
        model.addAttribute("object",new News());
        model.addAttribute("repo",service.findAllNews());
        return "index";
    }


    @PostMapping("/add")
    public String checkNewsInfo(@Valid News object, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("object",new News());
            model.addAttribute("repo",service.findAllNews());
            model.addAttribute("error","New don't save some binding error");
            return "index";
        }
        News m_news = new News();
        m_news.title = (String)bindingResult.getRawFieldValue("title");
        m_news.category = (String)bindingResult.getRawFieldValue("category");
        m_news.content = (String)bindingResult.getRawFieldValue("content");
        m_news.date=new Date();
        service.saveNews(m_news);
        model.addAttribute("object",object);
        model.addAttribute("repo",service.findAllNews());
        return "index";
    }

    @PostMapping("/delete")
    public String deleteByTitle(@Valid News object, BindingResult bindingResult, Model model){

        service.deleteNewsByTitle((String)bindingResult.getRawFieldValue("title"));
        model.addAttribute("object",object);
        model.addAttribute("repo",service.findAllNews());
        return "index";
    }

    @PostMapping("/findByNews")
    public String findByCategory(@Valid News object, BindingResult bindingResult, Model model){

        model.addAttribute("object",object);
        model.addAttribute("repo",service.findAllNewsByCategory((String)bindingResult.getRawFieldValue("category")));
        return "index";
    }

}
