package gato;

public class Person {

    private int age;
    private int score;
    private String idPlayer;
    private String nickName;

    public Person() {
        this.age = 0;
        this.score = 0;
        this.idPlayer = null;
        this.nickName = null;
    }

    public Person(int age, int score, String idPlayer, String nickName) {
        this.age = age;
        this.score = score;
        this.idPlayer = idPlayer;
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void showAllPerson(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] != null) {
                System.out.println("NickName: " + persons[i].getNickName());
                System.out.println("ID User: " + persons[i].getIdPlayer());
                System.out.println("Score: " + persons[i].getScore());
                System.out.println("Edad: " + persons[i].getAge());
            }
        }
    }

    public int search(Person[] persons, String name) {
        for (int i = 0; i < persons.length; i++) {
            if (persons[i] != null && persons[i].getNickName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void showPerson(Person[] persons, String name) {
        int i = search(persons, name);
        if (i != -1) {
            System.out.println("<____________________________________>");
            System.out.println(persons[i].getNickName());
            System.out.println("<------------------------------------>");
        }
    }
}
