package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.*;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
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

    @ManyToOne
    @JoinColumn(name = "playing_position_id")
    private PlayingPosition playingPosition;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
