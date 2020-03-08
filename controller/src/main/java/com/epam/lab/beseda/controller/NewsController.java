package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.NotEnoughArgumentsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.search.NewsSearchByAuthorCriteria;
import com.epam.lab.beseda.service.search.NewsSearchByTagsCriteria;
import com.epam.lab.beseda.service.serviceclass.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.*;

@RestController
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService service;

    @Autowired
    private NewsSearchByAuthorCriteria searchByAuthorCriteria;

    @Autowired
    private NewsSearchByTagsCriteria searchByTagsCriteria;

    @GetMapping("/count")
    public int getNewsNumber() {
        return service.getNewsNumber();
    }

    @GetMapping("/all")
    public List<NewsDTO> getAllNews() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NewsDTO getNews(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public NewsDTO addNews(@RequestBody NewsDTO newsDTO) throws ServiceLayerException {
        service.add(newsDTO);
        return newsDTO;
    }

    @PutMapping("/{id}")
    public NewsDTO updateNews(@PathVariable("id") int id, @RequestBody NewsDTO news) throws ServiceLayerException {
        news.setId(id);
        return service.update(news);
    }


    @GetMapping("/sort/{sortParam}")
    public List<NewsDTO> getAllNewsSortedByParameter(@PathVariable("sortParam") String param) throws ServiceLayerException {
        return service.getAllSorted(param);
    }

    @GetMapping("/search/author")
    public List<NewsDTO> findNewsByAuthor(@RequestBody AuthorDTO authorDTO) {
        return searchByAuthorCriteria.findByAuthor(authorDTO);
    }

    @GetMapping("/search/tags")
    public List<NewsDTO> findNewsByTags(@RequestBody List<String> tags) {
        return searchByTagsCriteria.findByTagList(tags);
    }

    @PutMapping("/{id}/tags/")
    public NewsDTO addNewsTags(@PathVariable int id, @RequestBody List<String> tags) throws ServiceLayerException {
        service.addNewsTags(id, tags);
        return service.getDtoById(id);
    }

    @DeleteMapping("/{id}/tags/")
    public NewsDTO deleteNewsTags(@PathVariable int id, @RequestBody List<String> tags) {
        service.deleteNewsTags(id, tags);
        return service.getDtoById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        service.delete(id);
        return NEWS_WITH_ID + id + IS_DELETED;
    }
}
