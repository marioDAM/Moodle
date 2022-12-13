package com.moodle.project.validations;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.regex.Pattern;

//public class Validacion {
//
//    private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
//    private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
//    private static final String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};
//
//    public static @NotNull(message = "Dni no puede ser nulo") String validarDNI(String dni) {
//        return String.valueOf(Arrays.binarySearch(INVALIDOS, dni) < 0 // (1)
//                && REGEXP.matcher(dni).matches() // (2)
//                && dni.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23)); // (3)
//    }
//
//}
