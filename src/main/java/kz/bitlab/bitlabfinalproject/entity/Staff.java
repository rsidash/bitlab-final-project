package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.*;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import kz.bitlab.bitlabfinalproject.enums.JobTitle;
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
    private Byte experience;
    @Column
    private String description;

    @Column(name = "job_title")
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
