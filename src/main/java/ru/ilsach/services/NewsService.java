package ru.ilsach.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilsach.models.News;
import ru.ilsach.repositories.NewsRepository;

import java.util.List;

/**
 * Created by ilsac on 30.04.2017.
 */
@Service
public class NewsService {
    @Autowired
    NewsRepository repository;
    @Transactional
    public News getNewsById(Long id){return repository.findById(id);}
    @Transactional
    public News getNewsByTitle(String title){return repository.findByTitle(title).get(0);}

    @Transactional
    public void saveNews(News news){repository.save(news);}

    @Transactional
    public List<News> findAllNews(){return repository.findAll();}

    @Transactional
    public List<News> findAllNewsByCategory(String category){return repository.findByCategory(category);}


    @Transactional
    public List<News> findAllNewsByTitle(String title){return repository.findByTitle(title);}


    @Transactional
    public List<News> findAllNewsByContent(String content){return repository.findByContent(content);}

    @Transactional
    public void deleteNewsByTitle(String title){repository.removeByTitle(title);}

    @Transactional
    public void deleteNewsById(Long id){repository.removeById(id);}

}
