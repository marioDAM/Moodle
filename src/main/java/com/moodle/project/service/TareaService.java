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
import java.util.List;
import java.util.Set;

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

    public Tarea createTarea(TareaDTO dto) {
        Tarea tarea = t.map(dto);
        return tareaRepository.save(tarea);
    }
}
