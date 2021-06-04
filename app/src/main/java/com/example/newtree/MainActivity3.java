package com.example.newtree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {


    int dateEndY, dateEndM, dateEndD;
    int ddayValue = 0;
    int temp_Y, temp_M, temp_D;


    Calendar calendar;
    int currentYear, currentMonth, currentDay;

    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    TextView edit_endDateBtn, edit_result;
    Button datePicker, alarmPicker;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private Button button_notify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = (calendar.get(Calendar.MONTH));
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        alarmPicker = (Button) findViewById(R.id.alarmPicker);
        datePicker = (Button) findViewById(R.id.datePicker);
        edit_endDateBtn = (TextView) findViewById(R.id.edit_endDateBtn);
        edit_result = (TextView) findViewById(R.id.edit_result);


        Locale.setDefault(Locale.KOREAN);


        edit_endDateBtn.setText(currentYear + "년 " + (currentMonth + 1) + "월 " + currentDay + "일");


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity3.this, endDateSetListener, (currentYear), (currentMonth), currentDay).show();
            }
        });

        button_notify = findViewById(R.id.alarmPicker);
        button_notify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendNotification();
            }
        });
        createNotificationChannel();


    }


    public void createNotificationChannel()
    {

        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        if(android.os.Build.VERSION.SDK_INT
                >= android.os.Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID
                    ,"Test Notification",mNotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }

    }


    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("D-DAY 알람")
                .setContentText(getDday(temp_Y, temp_M, temp_D))
                .setSmallIcon(R.drawable.ic_stat_name);

        return notifyBuilder;
    }


    public void sendNotification(){

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        mNotificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }



    public DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            edit_endDateBtn.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");

            ddayValue = ddayResult_int(dateEndY, dateEndM, dateEndD);

            edit_result.setText(getDday(year, monthOfYear, dayOfMonth));
            temp_Y = year;
            temp_M = monthOfYear;
            temp_D = dayOfMonth;
        }
    };


    private String getDday(int mYear, int mMonthOfYear, int mDayOfMonth) {


        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(mYear, mMonthOfYear, mDayOfMonth);

        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;


        String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "Today";
        } else {
            result *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, result));

        return strCount;
    }



    public int onCalculatorDate (int dateEndY, int dateEndM, int dateEndD) {
        try {
            Calendar today = Calendar.getInstance();
            Calendar dday = Calendar.getInstance();


            Calendar calendar = Calendar.getInstance();
            int cyear = calendar.get(Calendar.YEAR);
            int cmonth = (calendar.get(Calendar.MONTH) + 1);
            int cday = calendar.get(Calendar.DAY_OF_MONTH);

            today.set(cyear, cmonth, cday);
            dday.set(dateEndY, dateEndM, dateEndD);

            long day = dday.getTimeInMillis() / 86400000;


            long tday = today.getTimeInMillis() / 86400000;
            long count = tday - day;
            return (int) count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



    public int ddayResult_int(int dateEndY, int dateEndM, int dateEndD) {
        int result = 0;

        result = onCalculatorDate(dateEndY, dateEndM, dateEndD);

        return result;
    }
}