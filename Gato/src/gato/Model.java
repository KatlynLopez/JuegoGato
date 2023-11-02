package gato;

public class Model {

    char struct[][];
    private int numFilledCells;

    public Model() {
        this.struct = new char[3][3];
        this.numFilledCells = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.struct[i][j] = ' ';
            }
        }
    }

    public void setStruct(int i, int j, char letter) {
        struct[i][j] = letter;
    }

    public int getStruct(int i, int j) {
        return struct[i][j];
    }

    public boolean savePos(int i, int j, char letter) {
        if (struct[i][j] == ' ') {
            struct[i][j] = letter;
            numFilledCells++;
            return true;
        }
        return false;
    }

    public void showStructWithNumbers() {
        System.out.println("\t   " + struct[0][0] + " | " + struct[0][1] + 
                " | " + struct[0][2]);
        System.out.println("\t  ---|---|---");
        System.out.println("\t   " + struct[1][0] + " | " + struct[1][1] + 
                " | " + struct[1][2]);
        System.out.println("\t  ---|---|---");
        System.out.println("\t   " + struct[2][0] + " | " + struct[2][1] + 
                " | " + struct[2][2]);
    }

    public void clear() {
        numFilledCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                struct[i][j] = ' ';
            }
        }
    }

    public boolean isFull() {
        return numFilledCells == 9;
    }
}
