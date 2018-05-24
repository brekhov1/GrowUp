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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DataHelper;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    Button delData, delChil, repDemo, disKids;
    Switch notify;
    AlertDialog.Builder ad;
    private static final String MY_SETTINGS = "my_settings";
    static String title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);

        boolean notif = prefs.getBoolean("notify",false);

        notify = findViewById(R.id.switch1);

        if (notif) {
            notify.setChecked(true);
        } else {
            notify.setChecked(false);
        }

        delData = findViewById(R.id.delete_data);
        delChil = findViewById(R.id.delete_children);
        repDemo = findViewById(R.id.repeat_demo);
        disKids = findViewById(R.id.disable_kids);


        if (MainActivity.kids.getVisibility()==View.GONE) {
            disKids.setText("Включить картинку на главном экране");
            title = "Включить картинку";
            message = "Включить? Конечно, да!";
        } else {
            disKids.setText("Выключить картинку на главном экране");
            title = "Выключить картинку";
            message = "Вам надоела это картинка? :(";
        }

        delData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad = new AlertDialog.Builder(SettingsActivity.this);
                ad.setTitle("Удалить все записи");  // заголовок
                ad.setMessage("Вы хотите удалить все записи?"); // сообщение
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        DataHelper dataHelper = new DataHelper(getApplicationContext());
                        dataHelper.deleteAll();
                        MainActivity.fa.recreate();
                    }
                });
                ad.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });

                ad.show();

            }
        });

        delChil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad = new AlertDialog.Builder(SettingsActivity.this);
                ad.setTitle("Удалить всех детей");  // заголовок
                ad.setMessage("Вы хотите удалить всех детей?"); // сообщение
                ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                        ChildrenHelper childrenHelper = new ChildrenHelper(getApplicationContext());
                        childrenHelper.deleteAll();
                        MainActivity.fa.recreate();
                    }
                });
                ad.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });

                ad.show();

            }
        });

        repDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putBoolean("demo_show",true);
                prefEdit.commit();

                MainActivity.fa.recreate();
                finish();

            }
        });

        disKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad = new AlertDialog.Builder(SettingsActivity.this);
                ad.setTitle(title);  // заголовок
                ad.setMessage(message); // сообщение
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {



                        if (MainActivity.kids.getVisibility()==View.VISIBLE) {

                            MainActivity.kids.setVisibility(View.GONE);

                            SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefEdit = prefs.edit();

                            prefEdit.putBoolean("kids_pic_show",false);
                            prefEdit.apply();
                            MainActivity.fa.recreate();
                            finish();
                        } else {
                            MainActivity.kids.setVisibility(View.VISIBLE);

                            SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefEdit = prefs.edit();

                            prefEdit.putBoolean("kids_pic_show",true);
                            prefEdit.apply();
                            MainActivity.fa.recreate();
                            finish();
                        }

                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });

                ad.show();

            }
        });

        notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true) {
                    SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefEdit = prefs.edit();

                    prefEdit.putBoolean("notify",true);
                    prefEdit.apply();

                    Receiver receiver = new Receiver();
                    receiver.showNotification(getApplicationContext());

                    int REQUEST_CODE = 1234;
                    Intent intent = new Intent(SettingsActivity.this, Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, REQUEST_CODE, intent, 0);
                    AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                    am.setRepeating(am.RTC_WAKEUP, System.currentTimeMillis(), (am.INTERVAL_DAY)*7, pendingIntent);

                } else {
                    SharedPreferences prefs = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefEdit = prefs.edit();

                    prefEdit.putBoolean("notify",false);
                    prefEdit.apply();

                    int REQUEST_CODE = 1234;
                    Intent intent = new Intent(SettingsActivity.this, Receiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, REQUEST_CODE, intent, 0);
                    AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                    am.cancel(pendingIntent);
                }
            }
        });






    }
}
