package org.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterpreteComado {
    // Patrones RegEx para detectar acciones y enemigos/objetivos
    private static final Pattern ATACAR_PATRON = Pattern.compile("atacar\\s+a?l?\\s*(\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern MOVERSE_PATRON = Pattern.compile("moverse\\s+a\\s*(\\w+)", Pattern.CASE_INSENSITIVE);

    public static Comando procesarInput(String input) {
        Matcher matcherAtacar = ATACAR_PATRON.matcher(input);
        Matcher matcherMoverse = MOVERSE_PATRON.matcher(input);

        if (matcherAtacar.find()) {
            String objetivo = matcherAtacar.group(1); // Captura el objetivo después de "atacar"
            return new Comando(AccionJugador.ATACAR, objetivo);
        } else if (matcherMoverse.find()) {
            String destino = matcherMoverse.group(1); // Captura el lugar después de "moverse"
            return new Comando(AccionJugador.MOVERSE, destino);
        } else {
            return new Comando(AccionJugador.SALIR, null); // Comando por defecto si no se reconoce la acción
        }
    }

}
