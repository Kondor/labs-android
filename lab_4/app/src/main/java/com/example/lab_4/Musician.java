package com.example.lab_4;

public class Musician {
    private String name;
    private int age;
    private String genre;
    private int countMusic;
    private String instrument;
    private float moneyCondition;
    private String phone;

    public Musician(String name, int age, String genre, int countMusic, String instrument, float moneyCondition, String phone) {
        this.name = name;
        this.age = age;
        this.genre = genre;
        this.countMusic = countMusic;
        this.instrument = instrument;
        this.moneyCondition = moneyCondition;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Musician() {};

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCountMusic() {
        return countMusic;
    }

    public void setCountMusic(int countMusic) {
        this.countMusic = countMusic;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public float getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(float moneyCondition) {
        this.moneyCondition = moneyCondition;
    }
}
