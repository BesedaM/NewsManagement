package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.TagDTO;

public interface TagServiceInterface extends AbstractServiceInterface<TagDTO>{

    TagDTO getEntityByName(String name);
}
