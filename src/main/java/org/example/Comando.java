package org.example;

public class Comando {
    private AccionJugador accion;
    private String objetivo;

    public Comando(AccionJugador accion, String objetivo) {
        this.accion = accion;
        this.objetivo = objetivo;
    }

    public AccionJugador getAccion() {
        return accion;
    }

    public String getObjetivo() {
        return objetivo;
    }
}
