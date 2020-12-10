package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.UserDto;
import com.furkannsahin.parking.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @IterableMapping(qualifiedByName = "toUserDto")
    List<UserDto> toUserDtos(List<User> users);

    @Named("toUser")
    User toUser(UserDto userDto);

    @Named("toUserDto")
    UserDto toUserDto(User user);
}
