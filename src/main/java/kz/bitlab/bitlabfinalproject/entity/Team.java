package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.*;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import kz.bitlab.bitlabfinalproject.entity.security.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Team extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Staff> staff;
}
