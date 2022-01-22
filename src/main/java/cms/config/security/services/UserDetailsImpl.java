package cms.config.security.services;

import cms.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final String idClient;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(String idClient, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idClient = idClient;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getIdClient(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getIdClient() {
        return idClient;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(idClient, user.idClient);
    }
}
