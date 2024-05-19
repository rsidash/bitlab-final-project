package kz.bitlab.bitlabfinalproject.service.impl;

import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.PlayerRepository;
import kz.bitlab.bitlabfinalproject.service.PlayerService;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import kz.bitlab.bitlabfinalproject.service.mapper.PlayerMapper;
import kz.bitlab.bitlabfinalproject.service.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Override
    public List<PlayerDto> findAll() {
        final var players = playerRepository.findAll();

        return playerMapper.toDtoList(players);
    }

    @Override
    public List<PlayerDto> findByTeamName(String teamName) {
        final var players = playerRepository.findByTeamName(teamName);

        return playerMapper.toDtoList(players);
    }

    @Override
    public PlayerDto findByUuid(String uuid) {
        final var player = playerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return playerMapper.toDto(player);
    }

    @Override
    public void create(PlayerDto playerDto, String teamUuid) {
//        final var teamDto = teamService.findByUuid(teamUuid);
//        final var team = teamMapper.toEntityFromTeamDataDto(teamDto);
//        playerDto.setTeam(teamDto);

        final var player = playerMapper.toEntity(playerDto);

        playerRepository.save(player);
    }

    @Override
    public void update(String uuid, PlayerUpdateDto playerDto) {
        final var player = playerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        playerMapper.updateFromDto(playerDto, player);
    }

    @Override
    public void delete(String uuid) {
        playerRepository.findByUuid(uuid).ifPresentOrElse(
                player -> playerRepository.deleteById(player.getId()),
                () -> {
                    throw new NotFoundException("User not found");
                }
        );
    }
}
