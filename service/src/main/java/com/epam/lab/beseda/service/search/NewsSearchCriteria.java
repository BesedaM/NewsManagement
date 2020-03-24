package com.epam.lab.beseda.service.search;

import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.dao.entitydao.NewsDAO;
import com.epam.lab.beseda.service.modelmapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public abstract class NewsSearchCriteria {

    @Autowired
    protected NewsDAOInterface dao;

    @Autowired
    @Qualifier("newsMapper")
    protected NewsMapper mapper;

}
