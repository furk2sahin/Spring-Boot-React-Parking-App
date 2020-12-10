package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.BlacklistDto;
import com.furkannsahin.parking.model.Blacklist;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlacklistMapper {

    @IterableMapping(qualifiedByName = "toBlacklistDto")
    List<BlacklistDto> toBlacklistDtos(List<Blacklist> blacklists);

    @Named("toBlacklist")
    Blacklist toBlacklist(BlacklistDto blacklistDto);

    @Named("toBlacklistDto")
    BlacklistDto toBlacklistDto(Blacklist blacklist);
}
