package ru.ilsach.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ilsach.models.News;

import java.util.List;

/**
 * Created by ilsac on 30.04.2017.
 */
public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
    void removeByTitle(String title);
    List<News> findByCategory(String category);
    List<News> findAll();

}
