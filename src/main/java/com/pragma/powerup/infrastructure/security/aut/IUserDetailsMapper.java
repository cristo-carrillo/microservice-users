package com.pragma.powerup.infrastructure.security.aut;

import com.pragma.powerup.infrastructure.auth.UserAuthDto;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserDetailsMapper {
    DetailsUser toUser(UserEntity userEntity);
    UserEntity toUserEntity(DetailsUser user);
    UserAuthDto toUserAuth(DetailsUser user);
}
