package com.moodle.project.errors.usuarios;

public class NewUserWithDifferentPasswordsException extends RuntimeException {
    private static final long serialVersionUID = -7978601526802035152L;

    /**
     * Nos permite capturar el error del tipo que las contraseñas que el usuario está poniendo
     * no coinciden
     */
    public NewUserWithDifferentPasswordsException() {
        super("Las contraseñas no coinciden");
    }

}
