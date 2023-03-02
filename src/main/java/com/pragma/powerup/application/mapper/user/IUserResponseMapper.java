package com.pragma.powerup.application.mapper.user;

import com.pragma.powerup.application.dto.RolDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.dto.response.UserSaveResponseDto;
import com.pragma.powerup.application.mapper.rol.IRolDtoMapper;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRolDtoMapper.class})
public interface IUserResponseMapper {
    @Mapping(source = "userModel.id", target = "idDto")
    @Mapping(source = "userModel.name", target = "nameDto")
    @Mapping(source = "dto.id", target = "rolDto.id")
    @Mapping(source = "dto.name", target = "rolDto.name")
    @Mapping(ignore = true, target = "userModel.idRol")
    UserResponseDto toResponse(UserModel userModel, RolDto dto);

    UserSaveResponseDto toSaveResponse(UserModel userModel);
}
