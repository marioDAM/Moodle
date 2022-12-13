package com.moodle.project.mapper;

public interface IMapper<I, O> {
    /**
     * Interfaz que nos permite mapear el dato entrada en el dato de salida
     * @param in Dato entrada
     * @patam on Dato salida
     *
     * @return objeto mapeado de la entidad para trabajar con él
     */
    O map(I in);
}
