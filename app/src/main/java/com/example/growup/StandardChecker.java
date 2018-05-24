package com.example.growup;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.growup.DBHelpers.Standards;

import java.util.TreeMap;

public class StandardChecker {

    Standards standards = new Standards();
    Context context;

    public String[] check(int ageMonths, String sex, double height, double weight) {
        String[] result = {"nd","nd"};
        TreeMap<Integer,Double[]> heightStandards;
        TreeMap<Integer,Double[]> weightStandards;

        if (sex.equals("male")) {
            heightStandards = standards.getBoyHeight();
            weightStandards = standards.getBoyWeight();
        } else {
            heightStandards = standards.getGirlHeight();
            weightStandards = standards.getGirlWeight();
        }

        if (ageMonths<65) {

            while (heightStandards.get(ageMonths) == null) {ageMonths--;}

            Double[] heightArr = heightStandards.get(ageMonths);
            Double[] weightArr = weightStandards.get(ageMonths);

            if (height<heightArr[0])                        {result[0]="vlo";}
            if (height<heightArr[1]&&height>=heightArr[0])  {result[0]="lo";}
            if (height>=heightArr[1]&&height<=heightArr[2]) {result[0]="ok";}
            if (height>heightArr[2]&&height<=heightArr[3])  {result[0]="hi";}
            if (height>heightArr[3])                        {result[0]="vhi";}

            if (weight<weightArr[0])                        {result[1]="vlo";}
            if (weight<weightArr[1]&&weight>=weightArr[0])  {result[1]="lo";}
            if (weight>=weightArr[1]&&weight<=weightArr[2]) {result[1]="ok";}
            if (weight>weightArr[2]&&weight<=weightArr[3])  {result[1]="hi";}
            if (weight>weightArr[3])                        {result[1]="vhi";}
        }

        Log.e("CHECKER","RESULT: "+result[0]+"   "+result[1]);
        return result;
    }

}
