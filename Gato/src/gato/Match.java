package gato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Match {
    private boolean turn;
    private Model model;
    private Person persons[];

    public Match() {
        this.model = new Model();
    }

    public Match(boolean turn, Model model, Person[] persons) {
        this.turn = turn;
        this.model = model;
        this.persons = persons;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public boolean winner(char letter) {
        Model m = this.model;

        return (m.getStruct(0, 0) == letter && m.getStruct(0, 1) == 
                letter && m.getStruct(0, 2) == letter) ||
                (m.getStruct(1, 0) == letter && m.getStruct(1, 1) ==
                letter && m.getStruct(1, 2) == letter) ||
                (m.getStruct(2, 0) == letter && m.getStruct(2, 1) == 
                letter && m.getStruct(2, 2) == letter) ||
                (m.getStruct(0, 0) == letter && m.getStruct(1, 0) == 
                letter && m.getStruct(2, 0) == letter) ||
                (m.getStruct(0, 1) == letter && m.getStruct(1, 1) ==
                letter && m.getStruct(2, 1) == letter) ||
                (m.getStruct(0, 2) == letter && m.getStruct(1, 2) == 
                letter && m.getStruct(2, 2) == letter) ||
                (m.getStruct(0, 0) == letter && m.getStruct(1, 1) == 
                letter && m.getStruct(2, 2) == letter) ||
                (m.getStruct(2, 0) == letter && m.getStruct(1, 1) ==
                letter && m.getStruct(0, 2) == letter);
    }

   public void playerVsPlayer(Person p1, Person p2) throws IOException {
    int c = 0;
    int i;
    char letter;
    InputStreamReader r = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(r);

    do {
        System.out.println(" ");
        this.model.showStructWithNumbers();
        System.out.println(" ");

        String playerName = isTurn() ? p1.getNickName() : p2.getNickName();
        letter = isTurn() ? 'X' : 'O';

        System.out.print("Turno de " + playerName + ": elija una casilla [1-9]:"
                + " ");
        String input = br.readLine();
        i = Integer.parseInt(input) - 1;

        if (i >= 0 && i < 9 && this.model.savePos(i / 3, i % 3, letter)) {
            System.out.println(" ");
            this.model.showStructWithNumbers();
            System.out.println(" ");

            if (c >= 4) {
                if (winner(letter)) {
                    System.out.println(playerName + " Ha ganado, ¡felicidades!"
                    );
                    break;
                }
            }

            // Cambiar el turno al otro jugador
            setTurn(!isTurn());
        } else {
            System.out.println("Movimiento inválido, intente de nuevo.");
        }

        if (c == 8) {
            System.out.println("Hay un empate");
        }

        c++;
    } while (c < 9);
    this.model.clear();
}


    public void playerVscpu(Person p1) throws IOException {
        int c = 0;
        int i;
        char letter;
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        do {
            System.out.println(" ");
            this.model.showStructWithNumbers();
            System.out.println(" ");

            if (!isTurn()) {
                System.out.print("Turno de CPU: elija una casilla [1-9]: ");
                letter = 'O';
                cpuVsJuega(letter);
            } else {
                System.out.print("Turno de " + p1.getNickName() + ": elija una "
                        + "casilla [1-9]: ");
                String input = br.readLine();
                i = Integer.parseInt(input) - 1;
                letter = 'X';

                if (i >= 0 && i < 9 && this.model.savePos(i / 3, i % 3, letter))
                {
                    System.out.println(" ");
                    this.model.showStructWithNumbers();
                    System.out.println(" ");

                    if (c >= 3) {
                        if (winner(letter)) {
                            System.out.println(p1.getNickName() + " Ha ganado, "
                                    + "¡felicidades!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Movimiento inválido, intente de nuevo.")
                            ;
                }
            }

            setTurn(!isTurn());

            if (c == 8) {
                System.out.println("Hay un empate");
            }

            c++;
        } while (c < 9);
        this.model.clear();
    }

    public void cpuVsJuega(char letter) {
        Random rand = new Random();
        int i, j;
        Model m = this.model;

        do {
            i = rand.nextInt(3);
            j = rand.nextInt(3);
        } while (!m.savePos(i, j, letter));
    }

    public void cpuVsCpu() throws InterruptedException {
        char letter;
        int c = 0;
        Model m = this.model;

        do {
            if (!isTurn()) {
                letter = 'X';
                cpuVsJuega(letter);
            } else {
                letter = 'O';
                cpuVsJuega(letter);
            }

            System.out.println(" ");
            m.showStructWithNumbers();
            System.out.println(" ");

            if (c >= 3) {
                if (winner(letter)) {
                    System.out.println("Ha ganado la CPU");
                    break;
                }
            }

            setTurn(!isTurn());
            Thread.sleep(1000);
            c++;

        } while (c < 9);

        m.clear();
    }
}