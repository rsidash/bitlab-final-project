package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.*;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import kz.bitlab.bitlabfinalproject.enums.PlayingPosition;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "jersey_number")
    private byte jerseyNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private LocalDate birthdate;

    @Column(name = "playing_position")
    @Enumerated(EnumType.STRING)
    private PlayingPosition playingPosition;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
