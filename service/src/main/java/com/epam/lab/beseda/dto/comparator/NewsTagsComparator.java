package com.epam.lab.beseda.dto.comparator;

import com.epam.lab.beseda.dto.NewsDTO;

import java.util.Comparator;

public class NewsTagsComparator implements Comparator<NewsDTO> {

    @Override
    public int compare(NewsDTO o1, NewsDTO o2) {
        String newsTags01 = o1.getTags().toString().toLowerCase();
        String newsTags02 = o2.getTags().toString().toLowerCase();
        return newsTags01.compareTo(newsTags02);
    }
}
