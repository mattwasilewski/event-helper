package com.codecool.CodeCoolProjectGrande.user;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.user.passwordreset.ResetPasswordToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Builder
public class User implements UserDetails {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID userId = UUID.randomUUID();
    @NotNull
    private String name;
    private int age;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @Enumerated
    private UserType userType;
    private String imgUrl;
    private String location;
    @JoinColumn(name = "tokenId")
    @OneToOne(cascade=CascadeType.ALL)
    private ResetPasswordToken resetPasswordToken;
    @JoinColumn(name = "banId")
    @OneToOne(cascade=CascadeType.ALL)
    private BanToken banToken;

    @ManyToMany(mappedBy = "assignedUsers")
    private Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
