package org.example;

import org.example.Personajes.MainCharacter;
import org.example.Personajes.Npc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameState {
    private List<MainCharacter> personajes;
    private List<Npc> personajesNpc;
    private int turnoActual;
    private int monedas;

    public GameState() {
        this.personajes = new ArrayList<>();
        this.personajesNpc = new ArrayList<>();
        this.turnoActual = 0;
        this.monedas = 0;
    }

    // Método para agregar personajes jugables
    public void agregarPersonaje(MainCharacter personaje) {
        personajes.add(personaje);
    }

    // Método para agregar NPCs
    public void agregarNpc(Npc npc) {
        personajesNpc.add(npc);
    }

    // Método para obtener el personaje actual
    public MainCharacter getPersonajeActual() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes en el juego.");
            return null;
        }
        return personajes.get(0);
    }


    // Método para mostrar el estado actual de todos los personajes y NPCs
    public void mostrarEstado() {
        if (personajes.isEmpty() && personajesNpc.isEmpty()) {
            System.out.println("No hay personajes ni NPCs en el juego.");
            return;
        }

        System.out.println("Estado actual del juego:");

        for (MainCharacter p : personajes) {
            System.out.println(p);
        }

        for (Npc npc : personajesNpc) {
            System.out.println(npc);
        }
    }


    // Método para ganar monedas después de cada combate
    public void ganarMonedas() {
        int recompensa = (int) (Math.random() * 50 + 25); // Monedas entre 25 y 75
        monedas += recompensa;
        System.out.println("Has ganado " + recompensa + " monedas. Total: " + monedas + " monedas.");
    }

    // Método para visitar la tienda
    public void visitarTienda() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("¡Has encontrado una tienda misteriosa! Puedes comprar lo siguiente:");
            System.out.println("1. Pociones de vida (50 monedas)");
            System.out.println("2. Pociones de mana (50 monedas)");
            System.out.println("3. Mejorar habilidad especial (100 monedas)");
            System.out.println("4. Salir de la tienda");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1 -> {
                    if (monedas >= 50) {
                        monedas -= 50;
                        getPersonajeActual().restaurarSalud();
                        System.out.println("Has comprado una poción de vida.");
                    } else {
                        System.out.println("No tienes suficientes monedas.");
                    }
                }
                case 2 -> {
                    if (monedas >= 50) {
                        monedas -= 50;
                        getPersonajeActual().restaurarMana();
                        System.out.println("Has comprado una poción de mana.");
                    } else {
                        System.out.println("No tienes suficientes monedas.");
                    }
                }
                case 3 -> {
                    if (monedas >= 100) {
                        monedas -= 100;
                        getPersonajeActual().mejorarHabilidadEspecial();
                        System.out.println("Has mejorado tu habilidad especial.");
                    } else {
                        System.out.println("No tienes suficientes monedas.");
                    }
                }
                case 4 -> {
                    System.out.println("Has salido de la tienda.");
                    salir = true;
                }
                default -> System.out.println("Elección no válida.");
            }

            if (!salir) {
                System.out.println("Te quedan " + monedas + " monedas.");
            }
        }
    }

    // Método para remover un NPC
    public void removerNpc(Npc npc) {
        personajesNpc.remove(npc);
    }

    @Override
    public String toString() {
        return null;
    }
}


