package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.dto.NewsDTO;

import java.util.List;

public interface AuthorServiceInterface extends AbstractServiceInterface<AuthorDTO> {

    List<NewsDTO> getNews(int authorId);
}
