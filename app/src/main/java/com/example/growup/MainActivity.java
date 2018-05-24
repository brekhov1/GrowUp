package com.example.growup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.growup.Adapters.RVAdapterChildren;
import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DBChildren;
import com.example.growup.DBHelpers.DBData;
import com.example.growup.DBHelpers.DataHelper;

public class MainActivity extends AppCompatActivity {

    public static Activity fa;

    private RecyclerView recyclerView;
    TextView noEntries;
    ImageButton addBtn, setBtn;
    public static ImageView kids;

    private static final String MY_SETTINGS = "my_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fa = this;

        noEntries = findViewById(R.id.noEntries);
        addBtn = findViewById(R.id.add_toolbar);
        setBtn = findViewById(R.id.set_toolbar);
        kids = findViewById(R.id.kids_pic);

        SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();

        boolean demoShow = prefs.getBoolean("demo_show",true);
        boolean picture = prefs.getBoolean("kids_pic_show",true);

        if (demoShow) {
            Intent intent = new Intent(MainActivity.this, DemoActivity.class);
            prefEdit.putBoolean("demo_show",false);
            prefEdit.apply();
            startActivity(intent);
        }

        if (!picture) {
            kids.setVisibility(View.GONE);
        }

        ChildrenHelper childrenHelper = new ChildrenHelper(getApplicationContext());
        DBChildren dbc = new DBChildren(getApplicationContext());
        Cursor mCursor = dbc.getReadableDatabase().query("children", null, null, null, null, null, null);

        DataHelper dataHelper = new DataHelper(getApplicationContext());
        DBData dbd = new DBData(getApplicationContext());
        Cursor mCursor1 = dbd.getReadableDatabase().query("data", null, null, null, null, null, null);

        if (mCursor.getCount()==0) {noEntries.setVisibility(View.VISIBLE);} else { noEntries.setVisibility(View.GONE);}

        Log.e("a",childrenHelper.getTableAsString(dbc.getReadableDatabase(),"children"));
        Log.e("a",dataHelper.getTableAsString(dbd.getReadableDatabase(),"data"));

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new RVAdapterChildren(childrenHelper.getAll(), this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddChildActivity.class);
                startActivity(intent);
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

}

