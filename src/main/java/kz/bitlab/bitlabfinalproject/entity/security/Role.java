package kz.bitlab.bitlabfinalproject.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kz.bitlab.bitlabfinalproject.entity.core.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements GrantedAuthority {
    @Column
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
