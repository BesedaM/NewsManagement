package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.NewsDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.IS_DELETED;
import static com.epam.lab.beseda.util.ControllerMessage.NEWS_WITH_ID;

@RestController
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService service;

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

    @PutMapping("/{id}/")
    public List<String> addNewsTags(@RequestParam int id, @RequestBody List<String> tags) throws ServiceLayerException {
        service.addNewsTags(id, tags);
        return service.getNewsTagsNames(id);
    }

    @DeleteMapping("/{id}/")
    public List<String> deleteNewsTags(@RequestParam int id, @RequestBody List<String> tags) throws ServiceLayerException {
        service.deleteNewsTags(id, tags);
        return service.getNewsTagsNames(id);
    }


    @DeleteMapping("/{id}")
    public String deleteNews(@PathVariable("id") int id) {
        service.delete(id);
        return NEWS_WITH_ID + id + IS_DELETED;
    }
}
