package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.dto.EnumEntityDTO;

public interface EnumEntityServiceInterface extends AbstractServiceInterface<EnumEntityDTO>{

    EnumEntityDTO getEntityByName(String name);
}
