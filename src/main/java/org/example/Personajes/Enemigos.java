package org.example.Personajes;

public enum Enemigos {
    ANACONDA("Eunectes", 100, 20, 5),
    BESTIA("Bestia de tres cabezas", 200, 30, 20),
    DRAGON("Dragon Oscuro", 250, 30, 15),
    DRUIDA("Druida", 75, 20, 17),
    ESPECTRO("Espectro", 100, 25, 0),
    GOBLIN("Goblin", 75, 20, 8),
    GOLEM("Golem", 150, 20, 10),
    MAGOOSCURO("Mago Oscuro", 80, 25, 5);

    private String nombre;
    private int saludMax;
    private int fuerza;
    private int defensa;

    Enemigos(String nombre, int saludMax, int fuerza, int defensa) {
        this.nombre = nombre;
        this.saludMax = saludMax;
        this.fuerza = fuerza;
        this.defensa = defensa;
    }
}
