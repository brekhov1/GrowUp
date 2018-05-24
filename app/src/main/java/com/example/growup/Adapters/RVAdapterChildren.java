package com.example.growup.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.growup.ChildActivity;
import com.example.growup.DBHelpers.Child;
import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.MainActivity;
import com.example.growup.R;

import java.util.ArrayList;
import java.util.Calendar;

public class RVAdapterChildren extends RecyclerView.Adapter<RVAdapterChildren.ChildrenViewHolder> {

    private ArrayList<Child> children;
    private Context context;
    AlertDialog.Builder ad;

    public ChildrenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_child, parent, false);
        return new ChildrenViewHolder(view);
    }

    public RVAdapterChildren(ArrayList<Child> children, Context context) {
        this.children = children;
        this.context = context;
    }

    public void onBindViewHolder(final ChildrenViewHolder holder, int position) {

        Child child = children.get(position);
        holder.setRecord(child, position);

        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTimeInMillis(holder.child.getBirthday());
        } catch (NullPointerException e) {
            calendar.setTimeInMillis(0);
        }

        int mYear = calendar.get(Calendar.YEAR);
        int dayYear = calendar.get(Calendar.DAY_OF_YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        Log.e("CALENDAR",month+"m  BIRTH  d"+day);

        Calendar calendar1 = Calendar.getInstance();

        calendar1.setTimeInMillis(System.currentTimeMillis());
        int cYear = calendar1.get(Calendar.YEAR);
        int cDayYear = calendar1.get(Calendar.DAY_OF_YEAR);
        int cMonth = calendar1.get(Calendar.MONTH);
        int cDay = calendar1.get(Calendar.DAY_OF_MONTH);
//        Log.e("CALENDAR",cMonth+"m  CURRENT  d"+cDay);

        int years = 0;
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

        holder.name.setText(child.getName());
        holder.surname.setText(child.getSurname());
        holder.age.setText(years+" "+yrsMth);
        holder.data.setText(child.getLastHeight()+" см; "+child.getLastWeight()+" кг");

    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, age, data;
        CardView cardView;
        Child child;
        int position;

        public ChildrenViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            surname = view.findViewById(R.id.surname);
            age = view.findViewById(R.id.age);
            data = view.findViewById(R.id.data);
            cardView = view.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChildActivity.class);
                    intent.putExtra("child_id", position);
                    context.startActivity(intent);
                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    ad = new AlertDialog.Builder(context);
                    ad.setTitle("Удвлить ребёнка");  // заголовок
                    ad.setMessage("Вы хотите удалить ребёнка?"); // сообщение
                    ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            ChildrenHelper childrenHelper = new ChildrenHelper(context);
                            childrenHelper.delete(child.getId());
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

        void setRecord(Child child,int position){
            this.child = child;
            this.position = position;
        }
    }

}