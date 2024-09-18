package org.example;

import org.example.Personajes.MainCharacter;
import org.example.Personajes.Npc;
import java.util.Random;
import java.util.Scanner;

public class Combate {

    // Logica de combate
    public static void combate(GameState estado, Npc enemigo) {
        Scanner scanner = new Scanner(System.in);
        MainCharacter jugador = estado.getPersonajeActual();
        Random random = new Random();
        System.out.println("¡Prepárate para pelear!");

        while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
            estado.mostrarEstado();

            System.out.println("Es tu turno. Elige una acción:");
            System.out.println("1. Ataque normal");
            System.out.println("2. Habilidad especial");
            int accion = scanner.nextInt();

            int probabilidad = random.nextInt(100);

            if (accion == 1) {
                if (probabilidad < 75) {
                    int danio = jugador.atacar(enemigo);
                    System.out.println("Atacaste a " + enemigo.getNombre() + " y causaste " + danio + " de daño.");
                } else {
                    System.out.println("Has fallado el ataque.");
                }
            } else if (accion == 2) {
                if (probabilidad < 90) {
                    if (jugador.usarHabilidadEspecial(enemigo)) {
                        System.out.println("Usaste una habilidad especial en " + enemigo.getNombre() + ".");
                    } else {
                        System.out.println("Fallaste al usar tu habilidad especial.");
                    }
                }
            } else {
                System.out.println("Acción no válida.");
            }

            if (enemigo.getSalud() <= 0) {
                System.out.println("Has derrotado a " + enemigo.getNombre() + "!");
                estado.removerNpc(enemigo);
                break;
            }

            System.out.println("Es el turno del enemigo.");

            probabilidad = random.nextInt(100);

            if (probabilidad < 20) {
                enemigo.habilidadEspecialNpc(jugador);
                System.out.println("El enemigo " + enemigo.getNombre() + " usó su habilidad especial.");
            } else {
                int danioRecibido = enemigo.atacar(jugador);
                System.out.println("El enemigo " + enemigo.getNombre() + " te atacó y causó " + danioRecibido + " de daño.");
            }
            if (jugador.getSalud() <= 0) {
                System.out.println("Fin del juego.");
                break;
            }
            jugador.regenerarMana();
        }
    }
}
