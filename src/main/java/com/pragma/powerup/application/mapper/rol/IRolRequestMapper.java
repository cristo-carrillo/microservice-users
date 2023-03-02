package com.pragma.powerup.application.mapper.rol;

import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.domain.model.RolModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolRequestMapper {

    RolModel toRol(RolRequestDto rolRequestDto);

}
