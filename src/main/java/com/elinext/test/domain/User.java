package com.elinext.test.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "m_users")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "roles", "userReservations", "userPosition"})
@ToString(exclude = {"roles", "userReservations", "userPosition"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "position_id")
    private Long positionId;

    @OneToMany(mappedBy = "userRole")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roles = Collections.emptyList();

    @OneToMany(mappedBy = "userReservation")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> userReservations = Collections.emptyList();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    private Position userPosition;

    public boolean isAdmin() {
        return roles.contains("ROLE_ADMIN");
    }

    public boolean isUser() {
        return roles.contains("ROLE_USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
