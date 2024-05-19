package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.*;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import lombok.*;

@Entity
@Table(name = "staff")
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Staff extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private byte experience;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
