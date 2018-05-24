package com.example.growup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.growup.Adapters.RVAdapterChildren;
import com.example.growup.Adapters.RVAdapterData;
import com.example.growup.DBHelpers.Child;
import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DataHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ChildActivity extends AppCompatActivity {

    private Child child;
    private TextView name, surname, birthDate, age, hStandard, wStandard;
    private ImageButton addBtn, shareBtn, info;
    private CardView cardView;
    private ImageView pic;
    private RecyclerView recyclerView;

    static String dateStr;

    AlertDialog.Builder ad;

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        fa = this;

        int id = getIntent().getIntExtra("child_id",0);

        ChildrenHelper childrenHelper = new ChildrenHelper(getApplicationContext());

        child = childrenHelper.getChild(id);

        cardView = findViewById(R.id.cardViewChild);

        name = findViewById(R.id.name_child);
        surname = findViewById(R.id.surname_child);
        birthDate = findViewById(R.id.date_child);
        age = findViewById(R.id.age_child);
        addBtn = findViewById(R.id.add_toolbar);
        shareBtn = findViewById(R.id.share_toolbar);
        pic = findViewById(R.id.pic);
        info = findViewById(R.id.info);
        hStandard = findViewById(R.id.h_standard);
        wStandard = findViewById(R.id.w_standard);

        if (child.getSex().equals("male")) {
            pic.setImageResource(R.drawable.ic_account_circle_black_24dp);
        } else {
            pic.setImageResource(R.drawable.ic_account_circle_magenta_24dp);
        }

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTimeInMillis(child.getBirthday());
        } catch (NullPointerException e) {
            calendar.setTimeInMillis(0);
        }

        SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");

        int mYear = calendar.get(Calendar.YEAR);
        int dayYear = calendar.get(Calendar.DAY_OF_YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar1 = Calendar.getInstance();

        calendar1.setTimeInMillis(System.currentTimeMillis());
        int cYear = calendar1.get(Calendar.YEAR);
        int cDayYear = calendar1.get(Calendar.DAY_OF_YEAR);
        int cMonth = calendar1.get(Calendar.MONTH);
        int cDay = calendar1.get(Calendar.DAY_OF_MONTH);

        int years;
        String yrsMth = "лет";

        if (cDayYear < dayYear) {
            years = cYear-mYear-1;
        } else {
            years = cYear-mYear;
        }

        if ((years==1)) {yrsMth="год";}
        if ((years==2)||(years==3)||(years==4)) {yrsMth="года";}

        if (years==0) {
            yrsMth = "месяцев";
            years = cMonth-month;

            if (cDay < day) {
                years = cMonth-month-1;
            }
            if (years<0) {years+=12;}
            if (years==1) {yrsMth="месяц";}
            if ((years==2)||(years==3)||(years==4)) {yrsMth="месяцa";}
        }

        int ageMonths = 0;
        int temp = 0;

        if (yrsMth.equals("месяцев")||yrsMth.equals("месяц")) {
            ageMonths=years;
        } else {
            if (cDayYear < dayYear) {temp = cYear-mYear-1;} else {temp = cYear-mYear;}
            ageMonths = temp*12;

            if (cDay < day) {temp = cMonth-month-1;} else {temp = cMonth-month;}

            temp = Math.abs(temp);

            ageMonths+=temp;
        }

        StandardChecker checker = new StandardChecker();
        String[] checkResult = checker.check(ageMonths,child.getSex(),child.getLastHeight(),child.getLastWeight());

        switch (checkResult[0]) {
            case "vlo":
                hStandard.setText("НИЖЕ НОРМЫ");
                hStandard.setTextColor(Color.parseColor("#db0000"));
                break;
            case "lo":
                hStandard.setText("НИЗКИЙ");
                hStandard.setTextColor(Color.parseColor("#ff7400"));
                break;
            case "ok":
                hStandard.setText("НОРМА");
                hStandard.setTextColor(Color.parseColor("#00db00"));
                break;
            case "hi":
                hStandard.setText("ВЫСОКИЙ");
                hStandard.setTextColor(Color.parseColor("#ff7400"));
                break;
            case "vhi":
                hStandard.setText("ВЫШЕ НОРМЫ");
                hStandard.setTextColor(Color.parseColor("#db0000"));
                break;
            case "nd":
                hStandard.setText("Н/Д");
                hStandard.setTextColor(Color.GRAY);
                break;
        }

        switch (checkResult[1]) {
            case "vlo":
                wStandard.setText("НИЖЕ НОРМЫ");
                wStandard.setTextColor(Color.parseColor("#db0000"));
                break;
            case "lo":
                wStandard.setText("НИЗКИЙ");
                wStandard.setTextColor(Color.parseColor("#ff7400"));
                break;
            case "ok":
                wStandard.setText("НОРМА");
                wStandard.setTextColor(Color.parseColor("#00db00"));
                break;
            case "hi":
                wStandard.setText("ВЫСОКИЙ");
                wStandard.setTextColor(Color.parseColor("#ff7400"));
                break;
            case "vhi":
                wStandard.setText("ВЫШЕ НОРМЫ");
                wStandard.setTextColor(Color.parseColor("#db0000"));
                break;
            case "nd":
                wStandard.setText("Н/Д");
                wStandard.setTextColor(Color.GRAY);
                break;
        }

        Date date = new Date();
        date.setTime(calendar.getTimeInMillis());

        name.setText(child.getName());
        surname.setText(child.getSurname());
        birthDate.setText(parser.format(date));
        age.setText(years+" "+yrsMth);

        dateStr = parser.format(date);

        DataHelper dataHelper = new DataHelper(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerViewData);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new RVAdapterData(dataHelper.getAllByChild(child.getId()), this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildActivity.this, AddRecordActivity.class);
                intent.putExtra("parentId",child.getId());
                startActivity(intent);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Привет! Меня зовут "+child.getName()+" "+child.getSurname()+". Я родился "+dateStr+". Сейчас мой рост - "+child.getLastHeight()+" см, а моя масса - "+child.getLastWeight()+" кг!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ad = new AlertDialog.Builder(ChildActivity.this);
                ad.setTitle("Контроль стандартов");  // заголовок
                ad.setMessage("Р - рост; В - вес\nИнформация о стандартах развития детей действительна до 5 лет.\nДанные от Всемирной Организации Здравоохранения.");
                ad.setNegativeButton("Готово", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });

                ad.show();

            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {

                ad = new AlertDialog.Builder(ChildActivity.this);
                ad.setTitle("Удалить ребёнка");  // заголовок
                ad.setMessage("Вы хотите удалить ребёнка?"); // сообщение
                ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        ChildrenHelper childrenHelper = new ChildrenHelper(ChildActivity.this);
                        childrenHelper.delete(child.getId());
                        finish();
                        MainActivity.fa.recreate();
                    }
                });
                ad.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {

                    }
                });

                ad.show();

                return false;
            }
        });

    }
}
