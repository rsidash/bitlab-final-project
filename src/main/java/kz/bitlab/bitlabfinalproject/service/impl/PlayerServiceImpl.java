package kz.bitlab.bitlabfinalproject.service.impl;

import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerDto;
import kz.bitlab.bitlabfinalproject.entity.dto.player.PlayerUpdateDto;
import kz.bitlab.bitlabfinalproject.exception.NotFoundException;
import kz.bitlab.bitlabfinalproject.repository.PlayerRepository;
import kz.bitlab.bitlabfinalproject.service.PlayerService;
import kz.bitlab.bitlabfinalproject.service.TeamService;
import kz.bitlab.bitlabfinalproject.service.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final TeamService teamService;

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findAll() {
        final var players = playerRepository.findAll();

        return playerMapper.toDtoList(players);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlayerDto> findByTeamName(@NonNull final String teamName) {
        final var players = playerRepository.findByTeamNameOrderByLastName(teamName);

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
        final var team = teamService.findEntityByUuid(teamUuid);

        final var player = playerMapper.toEntity(playerDto);
        player.setTeam(team);

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
