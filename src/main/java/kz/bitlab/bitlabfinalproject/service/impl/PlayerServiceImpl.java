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
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findAll() {
        final var players = playerRepository.findAll();

        return playerMapper.toDtoList(players);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findByTeamName(@NonNull final String teamName) {
        final var players = playerRepository.findByTeamName(teamName);

        return playerMapper.toDtoList(players);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerDto findByUuid(@NonNull final String uuid) {
        final var player = playerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Player not found"));

        return playerMapper.toDto(player);
    }

    @Transactional
    @Override
    public void create(@NonNull final PlayerDto playerDto, @NonNull final String teamUuid) {
        final var teamDto = teamService.findByUuid(teamUuid);
        final var team = teamMapper.toEntityFromTeamDataDto(teamDto);

        final var player = playerMapper.toEntity(playerDto);
        player.setTeam(team);
        player.setUuid(String.valueOf(UUID.randomUUID()));

        playerRepository.save(player);
    }

    @Transactional
    @Override
    public void update(@NonNull final String uuid, @NonNull final PlayerUpdateDto playerDto) {
        final var player = playerRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundException("Player not found"));

        playerMapper.updateFromDto(playerDto, player);
        playerRepository.save(player);
    }

    @Transactional
    @Override
    public void delete(@NonNull final String uuid) {
        playerRepository.findByUuid(uuid).ifPresentOrElse(
                player -> playerRepository.deleteById(player.getId()),
                () -> {
                    throw new NotFoundException("Player not found");
                }
        );
    }
}
