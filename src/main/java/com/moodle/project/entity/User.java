package com.moodle.project.entity;

import com.moodle.project.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@MappedSuperclass
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(unique = true, name = "username")
    private String username;
    @Email(regexp = ".*@.*\\..*", message = "Email debe ser un email valido")
    @Column(unique = true, name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


}
