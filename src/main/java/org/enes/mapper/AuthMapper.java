package org.enes.mapper;

import org.enes.dto.request.AuthRegisterRequestDto;
import org.enes.entity.Auth;
import org.enes.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRequestDtoToAuth(AuthRegisterRequestDto authRegisterRequestDto);
}
