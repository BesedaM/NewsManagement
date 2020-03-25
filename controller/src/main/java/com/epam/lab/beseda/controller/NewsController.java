package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.search.NewsSearchByAuthorCriteria;
import com.epam.lab.beseda.service.search.NewsSearchByTagsCriteria;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import com.epam.lab.beseda.service.serviceclass.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/")
public class NewsController extends AbstractController<News, NewsDTO> {

    @Autowired
    private NewsSearchByAuthorCriteria searchByAuthorCriteria;

    @Autowired
    private NewsSearchByTagsCriteria searchByTagsCriteria;

    @Autowired
    @Override
    protected void setService(AbstractService<News, NewsDTO> service) {
        this.service = service;
    }

    @GetMapping("/count")
    public int getNewsNumber() {
        return ((NewsService) service).getNewsNumber();
    }

    @GetMapping("/sort/{sortParam}")
    public List<NewsDTO> getAllNewsSortedByParameter(@PathVariable("sortParam") String param) throws ServiceLayerException {
        return ((NewsService) service).getAllSorted(param);
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
        ((NewsService) service).addNewsTags(id, tags);
        return service.getDtoById(id);
    }

    @DeleteMapping("/{id}/tags/")
    public NewsDTO deleteNewsTags(@PathVariable int id, @RequestBody List<String> tags) {
        ((NewsService) service).deleteNewsTags(id, tags);
        return service.getDtoById(id);
    }

}
