package com.example.growup.DBHelpers;

import java.util.TreeMap;

public class Standards {

    TreeMap<Integer,Double[]> girlHeight = new TreeMap<>();
    TreeMap<Integer,Double[]> girlWeight = new TreeMap<>();

    TreeMap<Integer,Double[]> boyHeight = new TreeMap<>();
    TreeMap<Integer,Double[]> boyWeight = new TreeMap<>();

    public TreeMap<Integer,Double[]> getGirlHeight(){
        girlHeight.put(0,new Double[]{45.4,47.3,51.0,52.9});
        girlHeight.put(1,new Double[]{49.8,51.7,55.6,57.6});
        girlHeight.put(2,new Double[]{53.0,55.0,59.1,61.2});
        girlHeight.put(3,new Double[]{55.6,57.7,61.9,64.0});
        girlHeight.put(4,new Double[]{57.8,59.9,64.3,66.4});
        girlHeight.put(5,new Double[]{59.6,61.8,66.3,68.5});
        girlHeight.put(6,new Double[]{61.2,63.5,68.0,70.3});
        girlHeight.put(7,new Double[]{62.7,65.0,69.6,71.9});
        girlHeight.put(8,new Double[]{64.0,66.4,71.1,73.5});
        girlHeight.put(9,new Double[]{65.3,67.7,72.6,75.0});
        girlHeight.put(10,new Double[]{66.5,69.0,74.0,76.4});
        girlHeight.put(11,new Double[]{67.7,70.3,75.3,77.8});
        girlHeight.put(12,new Double[]{68.9,71.4,76.6,79.2});
        girlHeight.put(15,new Double[]{72.0,74.8,80.2,83.0});
        girlHeight.put(18,new Double[]{74.9,77.8,83.6,86.5});
        girlHeight.put(21,new Double[]{77.5,80.6,86.7,89.8});
        girlHeight.put(24,new Double[]{80.0,83.2,89.6,92.9});
        girlHeight.put(27,new Double[]{81.5,84.9,91.7,95.0});
        girlHeight.put(30,new Double[]{83.6,87.1,94.2,97.7});
        girlHeight.put(33,new Double[]{85.6,89.3,96.6,100.3});
        girlHeight.put(36,new Double[]{87.4,91.2,98.9,102.7});
        girlHeight.put(42,new Double[]{90.9,95.0,103.1,107.2});
        girlHeight.put(48,new Double[]{94.1,98.4,107.0,111.3});
        girlHeight.put(54,new Double[]{97.1,101.6,110.7,115.2});
        girlHeight.put(60,new Double[]{99.9,104.7,114.2,118.9});

        return girlHeight;
    }

    public TreeMap<Integer,Double[]> getGirlWeight() {
        girlWeight.put(0,new Double[]{2.4,2.8,3.7,4.2});
        girlWeight.put(1,new Double[]{3.2,3.6,4.8,5.5});
        girlWeight.put(2,new Double[]{3.9,4.5,5.8,6.6});
        girlWeight.put(3,new Double[]{4.5,5.2,6.6,7.5});
        girlWeight.put(4,new Double[]{5.0,5.7,7.3,8.2});
        girlWeight.put(5,new Double[]{5.4,6.1,7.8,8.8});
        girlWeight.put(6,new Double[]{5.7,6.5,8.3,9.4});
        girlWeight.put(7,new Double[]{6.0,6.8,8.6,9.8});
        girlWeight.put(8,new Double[]{6.2,7.0,9.0,10.2});
        girlWeight.put(9,new Double[]{6.5,7.3,9.3,10.6});
        girlWeight.put(10,new Double[]{6.7,7.5,9.6,10.9});
        girlWeight.put(11,new Double[]{6.9,7.7,9.9,11.2});
        girlWeight.put(12,new Double[]{7.0,7.9,10.1,11.5});
        girlWeight.put(15,new Double[]{7.6,8.5,10.9,12.4});
        girlWeight.put(18,new Double[]{8.1,9.1,11.6,13.2});
        girlWeight.put(21,new Double[]{8.6,9.6,12.3,14.0});
        girlWeight.put(24,new Double[]{9.0,10.2,13.0,14.8});
        girlWeight.put(27,new Double[]{9.5,10.7,13.7,15.7});
        girlWeight.put(30,new Double[]{10.0,11.2,14.4,16.5});
        girlWeight.put(33,new Double[]{10.4,11.7,15.1,17.3});
        girlWeight.put(36,new Double[]{10.8,12.2,15.8,18.1});
        girlWeight.put(42,new Double[]{11.6,16.1,17.2,19.8});
        girlWeight.put(48,new Double[]{12.3,14.0,18.5,21.5});
        girlWeight.put(54,new Double[]{13.0,14.9,19.9,23.2});
        girlWeight.put(60,new Double[]{13.7,15.8,21.2,24.9});

        return girlWeight;
    }

    public TreeMap<Integer,Double[]> getBoyHeight() {
        boyHeight.put(0,new Double[]{46.1,48.0,51.8,53.7});
        boyHeight.put(1,new Double[]{50.8,52.8,56.7,58.6});
        boyHeight.put(2,new Double[]{54.4,56.4,60.4,62.4});
        boyHeight.put(3,new Double[]{57.3,59.4,63.5,65.5});
        boyHeight.put(4,new Double[]{59.7,61.8,66.0,68.1});
        boyHeight.put(5,new Double[]{61.7,63.8,68.0,70.1});
        boyHeight.put(6,new Double[]{63.3,65.5,69.8,71.9});
        boyHeight.put(7,new Double[]{64.8,67.0,71.3,73.5});
        boyHeight.put(8,new Double[]{66.2,68.4,72.8,75.0});
        boyHeight.put(9,new Double[]{67.5,69.7,74.2,76.5});
        boyHeight.put(10,new Double[]{68.7,71.0,75.6,77.9});
        boyHeight.put(11,new Double[]{69.9,72.2,76.9,79.2});
        boyHeight.put(12,new Double[]{71.0,73.4,78.1,80.5});
        boyHeight.put(15,new Double[]{74.1,96.6,81.7,84.2});
        boyHeight.put(18,new Double[]{76.9,79.6,85.0,87.7});
        boyHeight.put(21,new Double[]{79.4,82.3,88.0,90.9});
        boyHeight.put(24,new Double[]{81.4,84.4,90.5,93.6});
        boyHeight.put(27,new Double[]{83.1,86.4,92.9,96.1});
        boyHeight.put(30,new Double[]{85.1,88.5,95.3,98.8});
        boyHeight.put(33,new Double[]{86.9,90.5,97.6,101.2});
        boyHeight.put(36,new Double[]{88.7,92.4,99.8,103.5});
        boyHeight.put(42,new Double[]{91.9,95.9,103.8,107.8});
        boyHeight.put(48,new Double[]{94.9,99.1,107.5,111.7});
        boyHeight.put(54,new Double[]{97.8,102.3,111.1,115.5});
        boyHeight.put(60,new Double[]{100.7,105.3,114.6,119.2});

        return boyHeight;
    }

    public TreeMap<Integer,Double[]> getBoyWeight() {
        boyWeight.put(0,new Double[]{2.5,2.9,3.9,4.4});
        boyWeight.put(1,new Double[]{3.4,3.9,5.1,5.8});
        boyWeight.put(2,new Double[]{4.3,4.9,6.3,7.1});
        boyWeight.put(3,new Double[]{5.0,5.7,7.2,8.0});
        boyWeight.put(4,new Double[]{5.6,6.3,7.8,8.8});
        boyWeight.put(5,new Double[]{6.0,6.7,8.4,9.4});
        boyWeight.put(6,new Double[]{6.3,7.1,8.9,9.9});
        boyWeight.put(7,new Double[]{6.6,7.4,9.3,10.3});
        boyWeight.put(8,new Double[]{6.9,7.7,9.6,10.7});
        boyWeight.put(9,new Double[]{7.1,8.0,9.9,11.1});
        boyWeight.put(10,new Double[]{7.4,8.2,10.2,11.4});
        boyWeight.put(11,new Double[]{7.5,8.4,10.5,11.7});
        boyWeight.put(12,new Double[]{7.7,8.7,10.8,12.0});
        boyWeight.put(15,new Double[]{8.3,9.2,11.5,12.8});
        boyWeight.put(18,new Double[]{8.8,9.8,12.2,13.7});
        boyWeight.put(21,new Double[]{9.2,10.3,12.9,14.5});
        boyWeight.put(24,new Double[]{9.7,10.8,13.6,15.3});
        boyWeight.put(27,new Double[]{10.1,11.3,14.3,16.1});
        boyWeight.put(30,new Double[]{10.5,11.8,15.0,16.9});
        boyWeight.put(33,new Double[]{10.9,12.3,15.6,17.6});
        boyWeight.put(36,new Double[]{11.3,12.7,16.2,18.3});
        boyWeight.put(42,new Double[]{12.0,13.6,17.4,19.7});
        boyWeight.put(48,new Double[]{12.7,14.4,18.6,21.2});
        boyWeight.put(54,new Double[]{13.4,15.2,19.8,22.7});
        boyWeight.put(60,new Double[]{14.1,16.0,21.0,24.2});

        return boyWeight;
    }

}
