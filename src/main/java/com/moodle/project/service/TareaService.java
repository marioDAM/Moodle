package com.moodle.project.service;

import com.moodle.project.dto.TareaDTO;
import com.moodle.project.entity.Tarea;
import com.moodle.project.entity.Usuario;
import com.moodle.project.mapper.TareaDTOtoTarea;
import com.moodle.project.repository.TareaRepository;
import com.moodle.project.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.moodle.project.enums.Role.STUDE;

@Service
public class TareaService {
    @Autowired
    private UsuariosRepository usuarioRepository;
    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private TareaDTOtoTarea t;

    @Transactional
    public Tarea modificaTarea(Long idTarea, String nuevoTitulo) throws Exception {
        Tarea tarea = tareaRepository.findById(idTarea).orElse(null);
        if (tarea == null) {
            throw new Exception("No existe tarea con id " + idTarea);
        }
        tarea.setTitulo(nuevoTitulo);
        tareaRepository.save(tarea);
        return tarea;
    }

    public List<Tarea> getTareas() {

        return (List<Tarea>) tareaRepository.findAll();

    }

    public Tarea createTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }
}
