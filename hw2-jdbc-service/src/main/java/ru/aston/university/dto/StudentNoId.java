package ru.aston.university.dto;

public class StudentNoId {

    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public StudentNoId(String name, int age, double score, boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
    }

    public StudentNoId() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public void setOlympicGamer(boolean olympicGamer) {
        this.olympicGamer = olympicGamer;
    }
}
