package com.example.growup;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.growup.DBHelpers.DataHelper;

public class DemoActivity extends AppCompatActivity {

    AlertDialog.Builder ad;
    ImageButton add;
    private static final String MY_SETTINGS = "my_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        add = findViewById(R.id.add_toolbar);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad = new AlertDialog.Builder(DemoActivity.this);
                ad.setTitle("Включить уведомления");  // заголовок
                ad.setMessage("Вы хотите получать напоминания о добавлении измерений каждую неделю?\nОтключить их можно в настройках"); // сообщение
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEdit = prefs.edit();

                        prefEdit.putBoolean("notify",true);
                        prefEdit.apply();

                        int REQUEST_CODE = 1234;
                        Intent intent = new Intent(DemoActivity.this, Receiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(DemoActivity.this, REQUEST_CODE, intent, 0);
                        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                        am.setRepeating(am.RTC_WAKEUP, System.currentTimeMillis(), (am.INTERVAL_DAY)*7, pendingIntent);

                        Intent intent1 = new Intent(DemoActivity.this, AddChildActivity.class);
                        finish();
                        startActivity(intent1);
                    }
                });
                ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        Intent intent = new Intent(DemoActivity.this, AddChildActivity.class);
                        finish();
                        startActivity(intent);

                    }
                });

                SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                ad.show();

            }
        });

    }
}
