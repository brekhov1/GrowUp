package com.example.growup.DBHelpers;

public class DataRecord {

    int id;
    int parentId;
    long time;
    double height;
    double weight;

    public DataRecord(int id, int parentId, long time, double height, double weight) {
        this.id = id;
        this.parentId = parentId;
        this.time = time;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}




