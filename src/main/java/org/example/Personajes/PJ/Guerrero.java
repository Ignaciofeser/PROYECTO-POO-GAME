package org.example.Personajes.PJ;

import org.example.Personaje;
import org.example.Personajes.MainCharacter;

public class Guerrero extends MainCharacter {
    public Guerrero() {
        super("Guerrero", 160, 30, 18, 25, 50, 25, 1);
    }

    @Override
    public void habilidadEspecial(Personaje objetivo) {
        int danio = (int) (getFuerza() * 2 * getMultiplicadorHabilidadEspecial());
        System.out.println(getNombre() + " usa Golpe Devastador, causando " + danio + " de daño a " + objetivo.getNombre());
        objetivo.recibirDanio(danio);
    }
}