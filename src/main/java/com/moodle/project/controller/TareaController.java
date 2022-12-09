//package com.moodle.project.controller;
//
//import com.moodle.project.dto.TareaDTO;
//import com.moodle.project.entity.Tarea;
//import com.moodle.project.service.TareaService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("tareas")
//public class TareaController {
//    @Autowired
//    private TareaService service;
//
//    @ApiOperation(value = "Crea una tarea")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Tarea creado"), @ApiResponse(code = 400, message = "Error al crear tarea")})
//    @PostMapping("/")
//    public Tarea nuevaTarea(@RequestBody TareaDTO dto) {
//        return service.createTarea(dto);
//    }
//
//    @ApiOperation(value = "Modifica una tarea")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Tarea modificada"), @ApiResponse(code = 400, message = "Error al modificar tarea")})
//    @PostMapping("/{idTarea}/editar")
//    public Tarea updateTarea(@PathVariable Long idTarea, @RequestBody String nuevoTitulo) throws Exception {
//        return service.modificaTarea(idTarea, nuevoTitulo);
//    }
//}
