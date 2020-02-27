package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import org.springframework.stereotype.Component;

@Component
public class EnumEntityMapper extends AbstractMapper<EnumEntity, EnumEntityDTO> {

    public EnumEntityMapper() {
        super(EnumEntity.class, EnumEntityDTO.class);
    }
}
