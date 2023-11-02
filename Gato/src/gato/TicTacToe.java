package gato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {

    static Person[] persons = new Person[20];

    public static void main(String[] args) throws IOException, 
            InterruptedException {
        boolean salir = false;
        int j = 0;

        String opc;

        Match game = new Match();

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        while (!salir) {
            System.out.println("Bienvenido al juego de Gato (Tic-Tac-Toe)");
            System.out.println("Menú principal");
            System.out.println("1: Partida Jugador vs Jugador");
            System.out.println("2: Registrarse Jugador");
            System.out.println("3: Partida Jugador vs CPU");
            System.out.println("4: Partida CPU vs CPU");
            System.out.println("5: Salir");
            System.out.println("Ingrese una opción: ");

            opc = br.readLine();

            switch (opc) {
                case "1":
                    String nickName1;
                    String nickName2;

                    System.out.println("Digite el nombre del primer Jugador");
                    nickName1 = br.readLine();

                    System.out.println("Digite el nombre del segundo Jugador");
                    nickName2 = br.readLine();

                    int n1 = search(nickName1);
                    int n2 = search(nickName2);

                    if (n1 != -1 && n2 != -1) {
                        System.out.println("Iniciando Partida");
                        System.out.println("\t" + nickName1 + " VS " +
                                nickName2);
                        System.out.println("El jugador " + nickName1 + 
                                " utiliza la letra 'X' y el jugador " 
                                + nickName2 + " con la letra 'O'");
                        System.out.println(nickName1 + " Elija una casilla "
                                + "[1-9].");

                        game.playerVsPlayer(persons[n1], persons[n2]);
                    } else {
                        System.out.println("Error, uno o ambos nombres son "
                                + "incorrectos.");
                    }
                    break;

                case "2":
                    int edad;
                    int score = 0;
                    String ide;
                    String name;

                    System.out.println("Digite el nombre o apodo del Jugador");
                    name = br.readLine();

                    System.out.println("Digite el ID del jugador");
                    ide = br.readLine();

                    System.out.println("Digite la edad");
                    edad = Integer.parseInt(br.readLine());

                    persons[j] = new Person(edad, score, ide, name);
                    j++;
                    break;

                case "3":
                    String nickName;
                    System.out.println("Digite el nombre del Jugador");
                    nickName = br.readLine();
                    int nn = search(nickName);

                    if (nn != -1) {
                        System.out.println("Iniciando Partida");
                        System.out.println("\t" + nickName + " VS CPU");
                        System.out.println("El jugador " + nickName + 
                                " utiliza la letra 'X' y la CPU la letra 'O'");
                        System.out.println(nickName + " Elija una casilla"
                                + " [1-9].");

                        game.playerVscpu(persons[nn]);
                    } else {
                        System.out.println("Error, el nombre es incorrecto.");
                    }
                    break;

                case "4":
                    System.out.println("CPU1 vs CPU2");
                    System.out.println("La CPU1 juega con 'X' y la CPU2 con "
                            + "'O'");

                    game.cpuVsCpu();
                    break;

                case "5":
                    salir = true;
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una"
                            + " opción del menú.");
                    break;
            }
        }

        System.out.println("El programa ha finalizado.");
    }

    public static int search(String name) {
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] != null && persons[i].getNickName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
