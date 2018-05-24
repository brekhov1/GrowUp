package com.example.growup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DataHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddChildActivity extends AppCompatActivity {

    EditText name, surname, date, height, weight;
    RadioGroup radioGroup;
    RadioButton radM,radF;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        name = findViewById(R.id.name_add);
        surname = findViewById(R.id.surname_add);
        date = findViewById(R.id.date_add);
        height = findViewById(R.id.height_add);
        weight = findViewById(R.id.weight_add);
        radioGroup = findViewById(R.id.radioGroup);
        radM = findViewById(R.id.radioMale);
        radF = findViewById(R.id.radioFemale);
        save = findViewById(R.id.save_add);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex;

                if (radM.isChecked()) {
                    sex = "male";
                } else {
                    sex = "female";
                }

                double wt = 0;
                double ht = 0;

                if (radM.isChecked()) {sex="male";} else {sex="female";}

                try {wt = Double.parseDouble(weight.getText().toString());} catch (Exception e){wt = 0;}

                try {ht = Double.parseDouble(height.getText().toString());} catch (Exception e){ht = 0;}

                SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");

                Date date1 = new Date();

                try { date1 = parser.parse(date.getText().toString()); } catch (Exception e) {}

                long dateInMillis = date1.getTime();

                ChildrenHelper childrenHelper = new ChildrenHelper(getApplicationContext());
                DataHelper dataHelper = new DataHelper(getApplicationContext());

                String name1 = name.getText().toString();
                String surname1 = surname.getText().toString();

                int pid = (int) childrenHelper.insert(name1,surname1,sex,dateInMillis,ht,wt);

                dataHelper.insert(pid,System.currentTimeMillis(),ht,wt);

                finish();
                MainActivity.fa.recreate();
            }
        });


    }

}
