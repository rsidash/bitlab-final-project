package kz.bitlab.bitlabfinalproject.service.mapper;

import kz.bitlab.bitlabfinalproject.entity.Player;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    PlayerDto toDto(Player player);

    @Mapping(source = "firstName", target = "firstName")
    Player toEntity(PlayerDto playerDto);

    List<PlayerDto> toDtoList(List<Player> players);

    Set<PlayerDto> toDtoSet(List<Player> players);

    Set<Player> toEntitySet(List<PlayerDto> players);

    void updateFromDto(PlayerUpdateDto dto, @MappingTarget Player player);
}
