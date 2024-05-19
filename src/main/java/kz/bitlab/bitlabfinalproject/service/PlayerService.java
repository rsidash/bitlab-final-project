package kz.bitlab.bitlabfinalproject.service;

import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> findAll();
    List<PlayerDto> findByTeamName(String teamName);
    PlayerDto findByUuid(String uuid);
    void create(PlayerDto playerDto, String teamUuid);
    void update(String uuid, PlayerUpdateDto playerDto);
    void delete(String uuid);
}
