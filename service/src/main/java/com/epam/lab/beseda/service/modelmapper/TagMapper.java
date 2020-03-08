package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper extends AbstractMapper<Tag, TagDTO> {

    public TagMapper() {
        super(Tag.class, TagDTO.class);
    }
}
