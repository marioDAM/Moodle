package com.moodle.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tareas")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;

    private String descripcion;

    // Relación muchos-a-uno entre tareas y usuario
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    // Nombre de la columna en la BD que guarda físicamente
    // el ID del usuario con el que está asociado una tarea
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    // Constructor vacío necesario para JPA/Hibernate.
    // No debe usarse desde la aplicación.

    // Al crear una tarea la asociamos automáticamente a un
    // usuario. Actualizamos, por tanto, la lista de tareas del
    // usuario.
//    public Tarea(Usuario usuario, String titulo) {
//        this.usuario = usuario;
//        this.titulo = titulo;
//        usuario.getTareas().add(this);
//    }
//
//    public Tarea(Long id, String titulo, String descripcion, Usuario usuario) {
//        this.id = id;
//        this.titulo = titulo;
//        this.descripcion = descripcion;
//        this.usuario = usuario;
//    }
//
//    public Tarea() {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        if (id != null && tarea.id != null)
            // Si tenemos los ID, comparamos por ID
            return Objects.equals(id, tarea.id);
        // sino comparamos por campos obligatorios
        return titulo.equals(tarea.titulo) && usuario.equals(tarea.usuario);
    }

    @Override
    public String toString() {
        return descripcion + '\'';
    }
}