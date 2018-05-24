package com.example.growup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DataHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRecordActivity extends AppCompatActivity {

    EditText height, weight;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        final int parentId = getIntent().getIntExtra("parentId",0);

        height = findViewById(R.id.height_add_record);
        weight = findViewById(R.id.weight_add_record);
        save = findViewById(R.id.save_add_record);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHelper dataHelper = new DataHelper(getApplicationContext());
                ChildrenHelper childrenHelper = new ChildrenHelper(getApplicationContext());

                double ht = 0;
                try {ht = Double.parseDouble(height.getText().toString());} catch (Exception e) {}
                double wt = 0;
                try {wt = Double.parseDouble(weight.getText().toString());} catch (Exception e) {}

                dataHelper.insert(parentId,System.currentTimeMillis(),ht,wt);
                childrenHelper.updateLast(parentId,ht,wt);

                finish();
                MainActivity.fa.recreate();
                ChildActivity.fa.recreate();

            }
        });



    }
}
