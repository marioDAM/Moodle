package com.moodle.project.mapper;

import com.moodle.project.dto.TareaDTO;
import com.moodle.project.entity.Tarea;
import org.springframework.stereotype.Component;

@Component
public class TareaDTOtoTarea implements IMapper<TareaDTO, Tarea> {
    @Override
    public Tarea map(TareaDTO in) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(in.getTitulo());
        tarea.setDescripcion(in.getDescripcion());
        tarea.setUsuario(in.getUsuario());
        return tarea;
    }
}
