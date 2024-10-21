package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AccionJugador {
    ATACAR("atacar\\s+(.+)"),
    MOVERSE("moverse\\s+a\\s+(.+)"),
    USAR_HABILIDAD("usar habilidad especial\\s+contra\\s+(.+)"),
    SALIR("salir");

    private final Pattern patron;

    // Constructor para asociar una expresión regular con cada acción
    AccionJugador(String regex) {
        this.patron = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    // Método para obtener la acción y el objetivo basado en el input del jugador
    public static Comando interpretarAccion(String input) {
        for (AccionJugador accion : values()) {
            Matcher matcher = accion.patron.matcher(input);
            if (matcher.matches()) {
                String objetivo = matcher.groupCount() > 0 ? matcher.group(1) : null;
                return new Comando(accion, objetivo);
            }
        }
        return null; // Retorna null si no se reconoce la acción
    }
}
