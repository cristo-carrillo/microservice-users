package com.pragma.powerup.application.mapper.rol;

import com.pragma.powerup.application.dto.RolDto;
import com.pragma.powerup.domain.model.RolModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolDtoMapper {
    RolDto toDto(RolModel rolModel);
}
