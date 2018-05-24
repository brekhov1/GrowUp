package com.example.growup.DBHelpers;

public class Child {

    int id;
    String name;
    String surname;
    String sex;
    long birthday;
    double lastHeight;
    double lastWeight;

    public Child(int id, String name, String surname, String sex, long birthday, double lastHeight, double lastWeight) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.lastHeight = lastHeight;
        this.lastWeight = lastWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public double getLastHeight() {
        return lastHeight;
    }

    public void setLastHeight(double lastHeight) {
        this.lastHeight = lastHeight;
    }

    public double getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(double lastWeight) {
        this.lastWeight = lastWeight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
