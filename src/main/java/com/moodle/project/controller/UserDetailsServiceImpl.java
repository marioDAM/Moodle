package com.moodle.project.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("STUDE".equals(username)) {               return userBuilder(username, new BCryptPasswordEncoder().encode("123456"), "STUDE");
        } else if ("TEACH".equals(username)) {
            return userBuilder(username, new BCryptPasswordEncoder().encode("123456"), "TEACH", "STUDE");
        } else if ("ADMIN".equals(username)) {
            return userBuilder(username, new BCryptPasswordEncoder().encode("123456"), "TEACH", "STUDE", "ADMIN");
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    private User userBuilder(String username, String password, String... roles) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accounyNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accounyNonLocked, authorities);

    }
}
