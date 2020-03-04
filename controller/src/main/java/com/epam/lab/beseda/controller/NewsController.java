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

    @GetMapping("/sort/{sortParam}")
    public List<NewsDTO> getAllNewsSortedByParameter(@PathVariable("sortParam") String param) throws ServiceLayerException {
        return service.getAllSorted(param);
    }

    @GetMapping("/search/author")
    public List<NewsDTO> findNewsByAuthor(@RequestBody AuthorDTO authorDTO) throws NotEnoughArgumentsException {
        List<NewsDTO> newsDTOList = null;
        if (authorDTO.getName() != null && authorDTO.getSurname() != null) {
            newsDTOList = searchByAuthorCriteria.findByAuthor(authorDTO);
        } else {
            throw new NotEnoughArgumentsException(FULL_AUTHOR_DATA_REQUIRED);
        }
        return newsDTOList;
    }

    @GetMapping("/search/tags")
    public List<NewsDTO> findNewsByTags(@RequestBody List<String> tags) throws NotEnoughArgumentsException {
        List<NewsDTO> newsDTOList = null;
        if (tags != null && tags.size() > 0) {
            newsDTOList = searchByTagsCriteria.findByTagList(tags);
        } else {
            throw new NotEnoughArgumentsException(TAGS_LIST_REQUIRED);
        }
        return newsDTOList;
    }


    @GetMapping("/{id}")
    public NewsDTO getNews(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public NewsDTO addNews(@RequestBody NewsDTO newsDTO) throws ServiceLayerException, NotEnoughArgumentsException {
        if (newsDTO.getAuthor() == null
                || newsDTO.getTitle() == null
                || newsDTO.getShortText() == null
                || newsDTO.getFullText() == null) {
            throw new NotEnoughArgumentsException(NEWS_DATA_REQUIRED);
        }
        newsDTO.setCreationDate(LocalDate.now());
        newsDTO.setModificationDate(LocalDate.now());
        this.service.add(newsDTO);
        return newsDTO;
    }

    @PutMapping("/")
    public NewsDTO updateNews(@RequestBody NewsDTO news) throws ServiceLayerException {
        NewsDTO newsDTO = service.getDtoById(news.getId());
        if (news.getTitle() != null) {
            newsDTO.setTitle(news.getTitle());
        }
        if (news.getShortText() != null) {
            newsDTO.setShortText(news.getShortText());
        }
        if (news.getFullText() != null) {
            newsDTO.setFullText(news.getFullText());
        }
        if (news.getAuthor() != null) {
            newsDTO.setAuthor(news.getAuthor());
        }
        newsDTO.setModificationDate(LocalDate.now());
        service.update(newsDTO);
        return newsDTO;
    }

    @PutMapping("/tags/{id}/")
    public List<String> addNewsTags(@PathVariable int id, @RequestBody List<String> tags) throws ServiceLayerException {
        service.addNewsTags(id, tags);
        return service.getNewsTagsNames(id);
    }

    @DeleteMapping("/tags/{id}/")
    public List<String> deleteNewsTags(@PathVariable int id, @RequestBody List<String> tags) throws ServiceLayerException {
        service.deleteNewsTags(id, tags);
        return service.getNewsTagsNames(id);
    }


    @DeleteMapping("/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        service.delete(id);
        return NEWS_WITH_ID + id + IS_DELETED;
    }
}
