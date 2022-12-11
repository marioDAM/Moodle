package com.moodle.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.moodle.project.enums.Role;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Implementa UserDetails, neccesario para usar el servicio de usuarios de SpringBoot(Spring security), ver CustomUserDetailsService
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 6189678452627071360L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Username no puede estar vacío")
    private String username;

    @NotNull(message = "Password no puede ser nulo")
    private String password;

    // Definimos el tipo de fetch como EAGER para que
    // cualquier consulta que devuelve un usuario rellene automáticamente
    // toda su lista de tareas
    // CUIDADO!! No es recomendable hacerlo en aquellos casos en los
    // que la relación pueda traer a memoria una gran cantidad de entidades
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "usuario")
    Set<Tarea> tareas = new HashSet<>();
    private String avatar;
    @NotNull(message = "FullName no puede ser nulo")
    private String fullName;

    @Email(regexp = ".*@.*\\..*", message = "Email debe ser un email valido")
    private String email;

    @NotNull(message = "Dni no puede ser nulo")
    private String dni;

    private int note;

    private int test;

    private Date dateCompleted;

    private String course;

    private boolean isTerminated;
    private LocalDateTime entryDate = LocalDateTime.now();
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date leavingDate;

    private String dischargedBy;

    private String modifiedBy;

    private String subjects;


    // Conjunto de permisos que tiene
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tareas=" + tareas +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", note=" + note +
                ", test=" + test +
                ", dateCompleted=" + dateCompleted +
                ", course='" + course + '\'' +
                ", isTerminated=" + isTerminated +
                ", entryDate=" + entryDate +
                ", leavingDate=" + leavingDate +
                ", dischargedBy='" + dischargedBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", subjects='" + subjects + '\'' +
                ", roles=" + roles +
                ", createdAt=" + createdAt +
                ", lastPasswordChangeAt=" + lastPasswordChangeAt +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name())).collect(Collectors.toList());
    }

    /**
     * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
     * cuerpo a este método
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
     * cuerpo a este método
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * No vamos a gestionar la expiración de cuentas. De hacerse, se tendría que dar
     * cuerpo a este método
     */

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * No vamos a gestionar el bloqueo de cuentas. De hacerse, se tendría que dar
     * cuerpo a este método
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
        for (Tarea tarea : tareas) {
            tarea.setUsuario(this);
        }
    }
}
