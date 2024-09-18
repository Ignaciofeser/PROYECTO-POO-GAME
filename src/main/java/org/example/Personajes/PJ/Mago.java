package org.example.Personajes.PJ;

import org.example.Personaje;
import org.example.Personajes.MainCharacter;

public class Mago extends MainCharacter {
    public Mago() {
        super("Mago", 110, 20, 5, 100, 200, 50,1);}

    @Override
    public void habilidadEspecial(Personaje objetivo) {
        if (getMana() >= getManaHabilidad()) {
            System.out.println(getNombre() + " lanza una bola de fuego!");
            int danio = getFuerza() * 3;  // La bola de fuego hace 3 veces su fuerza en daño
            objetivo.recibirDanio(danio);
            System.out.println(objetivo.getNombre() + " recibe " + danio + " puntos de daño.");
            setMana(getMana() - getManaHabilidad());  // Consumir el mana necesario para la habilidad
        } else {
            System.out.println("No hay suficiente mana para lanzar la bola de fuego.");
        }
    }
}