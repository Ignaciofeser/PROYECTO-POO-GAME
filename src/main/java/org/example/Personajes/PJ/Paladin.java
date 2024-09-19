package org.example.Personajes.PJ;

import org.example.ConsoleColors;
import org.example.Personaje;
import org.example.Personajes.MainCharacter;

public class Paladin extends MainCharacter {
    public Paladin() {
        super("Paladin", 130, 25, 15, 60, 80, 20,1);
    }

    @Override
    public void habilidadEspecial(Personaje objetivo) {
        int saludRestaurada = (int) (getSaludMax() * 0.3 * getMultiplicadorHabilidadEspecial());
        setSalud(getSalud() + saludRestaurada);

        // Asegúrate de que la salud no exceda el máximo
        if (getSalud() > getSaludMax()) {
            setSalud(getSaludMax());
        }

        System.out.println(ConsoleColors.GREEN_BOLD + getNombre() + ConsoleColors.RESET + " invoca una bendición y se cura " + ConsoleColors.GREEN_BOLD_BRIGHT + saludRestaurada + ConsoleColors.RESET + " puntos de salud.");
    }
}