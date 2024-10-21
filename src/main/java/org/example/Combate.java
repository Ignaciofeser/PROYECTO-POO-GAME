package org.example;

import org.example.Personajes.MainCharacter;
import org.example.Personajes.Npc;
import org.example.Levenshtein;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
 // Ajusta la ruta según sea necesario


public class Combate {
    public static void combate(GameState estado, Npc enemigo) {
        Scanner scanner = new Scanner(System.in);
        MainCharacter jugador = estado.getPersonajeActual();
        Random random = new Random();
        System.out.println("¡Prepárate para pelear!");

        while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
            estado.mostrarEstado();

            System.out.println("Es tu turno. Escribe una acción (por ejemplo, 'atacar al goblin' o 'usar habilidad especial'):");
            String accion = scanner.nextLine().toLowerCase();
            boolean accionValida = false;

            // Dividir la acción en palabras
            String[] palabrasAccion = accion.split(" ");

            // Comprobar si alguna palabra es un sinónimo válido para "atacar"
            for (String palabra : palabrasAccion) {
                for (String sinonimo : ListaSinonimos.ACCION_ATACAR) {
                    if (Levenshtein.calcular(palabra, sinonimo) <= 3) {
                        accionValida = true;
                        break; // Salir del bucle si se encuentra un sinónimo
                    }
                }
                if (accionValida) break; // Salir del bucle externo si ya se encontró un sinónimo
            }

            // Comprobar si alguna palabra es un sinónimo válido para "habilidad especial"
            if (!accionValida) {
                for (String palabra : palabrasAccion) {
                    for (String sinonimo : ListaSinonimos.ACCION_HABILIDAD_ESPECIAL) {
                        if (Levenshtein.calcular(palabra, sinonimo) <= 3) {
                            accionValida = true;
                            break; // Salir del bucle si se encuentra un sinónimo
                        }
                    }
                    if (accionValida) break; // Salir del bucle externo si se encuentra un sinónimo
                }
            }

            if (accionValida) {
                // Procesar la acción válida
                if (ListaSinonimos.ACCION_ATACAR.stream().anyMatch(sinonimo -> Levenshtein.calcular(accion, sinonimo) <= 3)) {
                    int probabilidad = random.nextInt(100);
                    if (probabilidad < 75) {
                        int danio = jugador.atacar(enemigo);
                        System.out.println("Atacaste a " + enemigo.getNombre() + " y causaste " + danio + " de daño.");
                    } else {
                        System.out.println("Has fallado el ataque.");
                    }
                } else if (ListaSinonimos.ACCION_HABILIDAD_ESPECIAL.stream().anyMatch(sinonimo -> Levenshtein.calcular(accion, sinonimo) <= 3)) {
                    int probabilidad = random.nextInt(100);
                    if (probabilidad < 90) {
                        if (jugador.usarHabilidadEspecial(enemigo)) {
                            System.out.println("Usaste una habilidad especial en " + enemigo.getNombre() + ".");
                        } else {
                            System.out.println("Fallaste al usar tu habilidad especial.");
                        }
                    }
                }
            } else {
                System.out.println("Acción no válida o no reconocida.");
            }

            if (enemigo.getSalud() <= 0) {
                System.out.println("Has derrotado a " + enemigo.getNombre() + "!");
                estado.removerNpc(enemigo);
                break;
            }

            System.out.println("Es el turno del enemigo.");
            int probabilidad = random.nextInt(100);
            if (probabilidad < 20) {
                enemigo.habilidadEspecialNpc(jugador);
                System.out.println("El enemigo " + enemigo.getNombre() + " usó su habilidad especial.");
            } else {
                int danioRecibido = enemigo.atacar(jugador);
                System.out.println("El enemigo " + enemigo.getNombre() + " te atacó y causó " + danioRecibido + " de daño.");
            }

            if (jugador.getSalud() <= 0) {
                System.out.println("Has sido derrotado. ¡Fin del juego!");
                estado.setGameOver(true);
                break;
            }

            jugador.regenerarMana();
        }
    }
}


/*
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
        System.out.println();
        System.out.println(ConsoleColors.YELLOW_BACKGROUND + "¡Prepárate para pelear!" + ConsoleColors.RESET);
        System.out.println();

        while (jugador.getSalud() > 0 && enemigo.getSalud() > 0) {
            estado.mostrarEstado();

            System.out.println(ConsoleColors.YELLOW_UNDERLINED + "Es tu turno. Elige una acción:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "1. Ataque normal" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "2. Habilidad especial" + ConsoleColors.RESET);
            int accion = scanner.nextInt();

            int probabilidad = random.nextInt(100);

            if (accion == 1) {
                if (probabilidad < 75) {
                    int danio = jugador.atacar(enemigo);

                    System.out.println("Atacaste a " + enemigo.getNombre() + " y causaste " + ConsoleColors.GREEN_BOLD_BRIGHT + danio + ConsoleColors.RESET + " de daño.");
                  
                } else {
                    System.out.println(ConsoleColors.RED_BRIGHT + "Has fallado el ataque." + ConsoleColors.RESET);
                }
            } else if (accion == 2) {
                if (probabilidad < 90) {
                    if (jugador.usarHabilidadEspecial(enemigo)) {
                        System.out.println("Usaste una habilidad especial en " + enemigo.getNombre() + ".");
                    } else {
                        System.out.println(ConsoleColors.RED_BRIGHT + "Fallaste al usar tu habilidad especial." + ConsoleColors.RESET);
                    }
                }
            } else {
                System.out.println(ConsoleColors.RED_BRIGHT + "Acción no válida." + ConsoleColors.RESET);
            }

            if (enemigo.getSalud() <= 0) {
                System.out.println();
                System.out.println(ConsoleColors.WHITE_BACKGROUND + "Has derrotado a " + enemigo.getNombre() + "!" + ConsoleColors.RESET);
                System.out.println();
                estado.removerNpc(enemigo);
                break;
            }

            System.out.println(ConsoleColors.CYAN_BOLD + "Es el turno del enemigo." + ConsoleColors.RESET);

            probabilidad = random.nextInt(100);

            if (probabilidad < 20) {
                enemigo.habilidadEspecialNpc(jugador);
                System.out.println("El enemigo " + ConsoleColors.RED_BOLD + enemigo.getNombre() + ConsoleColors.RESET + " usó su habilidad especial.");
            } else {
                int danioRecibido = enemigo.atacar(jugador);
                System.out.println("El enemigo " + ConsoleColors.RED_BOLD + enemigo.getNombre() + ConsoleColors.RESET + " te atacó y causó " + ConsoleColors.RED_BRIGHT + danioRecibido + ConsoleColors.RESET + " de daño.");
            }

            if (jugador.getSalud() <= 0) {

                System.out.println(ConsoleColors.RED_BACKGROUND + "Fin del juego." + ConsoleColors.RESET);
                estado.setGameOver(true);

                break;
            }

            jugador.regenerarMana();
        }


    }
}



*/
