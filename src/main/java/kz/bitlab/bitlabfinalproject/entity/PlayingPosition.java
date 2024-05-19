package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import lombok.*;

@Entity
@Table(name = "playing_positions")
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayingPosition extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;
}
