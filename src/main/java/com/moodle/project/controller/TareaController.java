package com.moodle.project.controller;

import com.moodle.project.entity.Tarea;
import com.moodle.project.entity.Usuario;
import com.moodle.project.repository.UsuariosRepository;
import com.moodle.project.service.TareaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@RestController
@RequestMapping("tareas")
public class TareaController {
    @Autowired
    private TareaService service;
    @Autowired
    private UsuariosRepository usuarioService;

    /**
     *
     * @param tarea
     * @return
     */

    @ApiOperation(value = "Crea una tarea")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Tarea creado"), @ApiResponse(code = 400, message = "Error al crear tarea")})
    @PostMapping("/")
    public ResponseEntity<Tarea> nuevaTarea(@Valid @RequestBody Tarea tarea) {
        Optional<Usuario> usuario = usuarioService.findById(tarea.getUsuario().getId());
        if (!usuario.isPresent()) {
            System.err.println("No se ha podido procesar la petición");
        }
        tarea.setUsuario(usuario.get());
        Tarea newTarea = service.createTarea(tarea);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTarea.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(newTarea);
    }

    /**
     *
     * @param idTarea
     * @param nuevoTitulo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "Modifica una tarea")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Tarea modificada"), @ApiResponse(code = 400, message = "Error al modificar tarea")})
    @PostMapping("/{idTarea}/editar")
    public Tarea updateTarea(@PathVariable Long idTarea, @RequestBody String nuevoTitulo) throws Exception {
        return service.modificaTarea(idTarea, nuevoTitulo);
    }

    /**
     *
     * @return
     */
    @GetMapping("/tareas")
    public List<Tarea> getTareas() {
        return service.getTareas();
    }
}
