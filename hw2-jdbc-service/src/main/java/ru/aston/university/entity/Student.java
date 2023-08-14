package ru.aston.university.entity;

public class Student {

    private long id;
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

