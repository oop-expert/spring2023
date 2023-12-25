package com.app.my.domain.models;

import com.app.my.domain.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Valid

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    @NotNull(message = "email - обязательное поле")
    @NotBlank(message = "email - обязательное поле")
    private String email;
    @Column(name = "phone_number")
    @NotNull(message = "phoneNumber - обязательное поле")
    @NotBlank(message = "phoneNumber - обязательное поле")
    private String phoneNumber;
    @Column(name = "name")
    @NotNull(message = "name - обязательное поле")
    @NotBlank(message = "name - обязательное поле")
    private String name;
    @Column(name = "active")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="image_id")
    private Image avatar;
    @Column(name = "password")
    @NotNull(message = "password - обязательное поле")
    @NotBlank(message = "password - обязательное поле")
    @Size(min = 5, message = "Пароль должен состоять минимум из 5 символов")
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Product> products = new ArrayList<>();
    private LocalDateTime dateofCreated;

    @PrePersist
    private void init() {
        dateofCreated = LocalDateTime.now();
    }

    //security

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
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
        return active;
    }
}
