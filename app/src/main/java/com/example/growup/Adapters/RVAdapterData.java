package com.example.growup.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.growup.ChildActivity;
import com.example.growup.DBHelpers.Child;
import com.example.growup.DBHelpers.ChildrenHelper;
import com.example.growup.DBHelpers.DataHelper;
import com.example.growup.DBHelpers.DataRecord;
import com.example.growup.MainActivity;
import com.example.growup.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class RVAdapterData extends RecyclerView.Adapter<RVAdapterData.DataViewHolder> {

    private ArrayList<DataRecord> dataRecords;
    private Context context;
    AlertDialog.Builder ad;
    static String share, share1, share2;

    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new DataViewHolder(view);
    }

    public RVAdapterData(ArrayList<DataRecord> dataRecords, Context context) {

        Collections.reverse(dataRecords);

        this.dataRecords = dataRecords;
        this.context = context;
    }

    public void onBindViewHolder(final DataViewHolder holder, int position) {

        DataRecord dataRecord = dataRecords.get(position);
        holder.setRecord(dataRecord, position);

        ChildrenHelper childrenHelper = new ChildrenHelper(context);

        int childId = dataRecord.getParentId();

        long birthday = 0;

        birthday = childrenHelper.getChildBirthday(childId);

        Calendar calendar = Calendar.getInstance();

        try {calendar.setTimeInMillis(birthday);} catch (NullPointerException e) {calendar.setTimeInMillis(0);}

        int mYear = calendar.get(Calendar.YEAR);
        int dayYear = calendar.get(Calendar.DAY_OF_YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendar1 = Calendar.getInstance();

        try {calendar1.setTimeInMillis(dataRecord.getTime());} catch (Exception e) {calendar.setTimeInMillis(0);}

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

        SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        share = parser.format(calendar1.getTimeInMillis())+"";
        share1 = dataRecord.getHeight()+"";
        share2 = dataRecord.getWeight()+"";

        holder.age.setText("("+years+" "+yrsMth+")");
        holder.time.setText(parser.format(calendar1.getTimeInMillis())+"");
        holder.data.setText(dataRecord.getHeight()+" см; "+dataRecord.getWeight()+" кг");

    }

    @Override
    public int getItemCount() {
        return dataRecords.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView time, age, data;
        CardView cardView;
        ImageButton shareBtn;
        DataRecord dataRecord;
        int position;

        public DataViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time_data);
            age = view.findViewById(R.id.age_data);
            data = view.findViewById(R.id.data_child);
            cardView = view.findViewById(R.id.cardViewData);
            shareBtn = view.findViewById(R.id.share_data);

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Привет! "+share+" мой рост был "+share1+" см, а масса - "+share2+" кг!");
                    sendIntent.setType("text/plain");
                    context.startActivity(sendIntent);

                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    ad = new AlertDialog.Builder(context);
                    ad.setTitle("Удалить запись");  // заголовок
                    ad.setMessage("Вы хотите удалить запись?"); // сообщение
                    ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            DataHelper dataHelper = new DataHelper(context);
                            dataHelper.delete(dataRecord.getId());
                            ChildActivity.fa.recreate();
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

        void setRecord(DataRecord dataRecord,int position){
            this.dataRecord = dataRecord;
            this.position = position;
        }
    }

}