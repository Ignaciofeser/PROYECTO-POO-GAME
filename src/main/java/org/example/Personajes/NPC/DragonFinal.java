package org.example.Personajes.NPC;

import org.example.Personajes.MainCharacter;
import org.example.Personajes.Npc;

public class DragonFinal extends Npc {
    public DragonFinal() {super("Dragon Oscuro", 250, 30, 15);}
    public void habilidadEspecialNpc(MainCharacter jugador) {
        int danio = getFuerza() * 2;
        System.out.println(getNombre() + " usa Bola de Fuego, causando " + danio + " de daño a " + jugador.getNombre());
        jugador.recibirDanio(danio);
    }
}
