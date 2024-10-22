package org.example.Personajes;

public enum Personajes {
    GUERRERO("Guerrero", 160, 30, 18, 25, 50, 25, 1),
    MAGO("Mago", 110, 20, 5, 100, 200, 50,1),
    PALADIN("Paladin", 130, 25, 15, 60, 80, 20, 1);

    private String nombre;
    private int saludMax;
    private int fuerza;
    private int defensa;
    private int mana;
    private int manaMax;
    private int manaHabilidad;
    private double multiplicadorHabilidadEspecial;

    Personajes(String nombre, int saludMax, int fuerza, int defensa, int mana, int manaMax, int manaHabilidad, double multiplicadorHabilidadEspecial) {
        this.nombre = nombre;
        this.saludMax = saludMax;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.mana = mana;
        this.manaMax = manaMax;
        this.manaHabilidad = manaHabilidad;
        this.multiplicadorHabilidadEspecial = multiplicadorHabilidadEspecial;
    }
}
