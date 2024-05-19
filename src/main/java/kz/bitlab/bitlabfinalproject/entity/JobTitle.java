package kz.bitlab.bitlabfinalproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import lombok.*;

@Entity
@Table(name = "job_titles")
@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JobTitle extends BaseEntity {
    @Column(nullable = false)
    private String name;
}
