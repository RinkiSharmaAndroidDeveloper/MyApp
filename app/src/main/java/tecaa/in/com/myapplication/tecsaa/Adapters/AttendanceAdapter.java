package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.content.Context;

import android.graphics.Color;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import tecaa.in.com.myapplication.tecsaa.DrawableUtils;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private List<ClassWorkModel> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, time, Name, subject, link, textSubject;

        CalendarView calendarView;

        public MyViewHolder(View view) {
            super(view);
            //title = (TextView) view.findViewById(R.id.title);
            calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        }
    }


    public AttendanceAdapter( Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_attendace, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        List<EventDay> events = new ArrayList<>();

        Calendar calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
            events.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(context, "L")));

            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DAY_OF_MONTH, 10);
            events.add(new EventDay(calendar1, R.drawable.sample_circle_one));

            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.DAY_OF_MONTH, 10);
            events.add(new EventDay(calendar2, R.drawable.sample_circle_one, Color.parseColor("#228B22")));
    /*     //   android.icu.util.Calendar c = android.icu.util.Calendar.getInstance();
            Calendar c = Calendar.getInstance();
            Long min = c.getTime().getTime();
            Long max = 2629746000L + c.getTime().getTime();*/
            Calendar min = Calendar.getInstance();
            min.add(Calendar.MONTH, -2);

            Calendar max = Calendar.getInstance();
            max.add(Calendar.MONTH, 2);

            holder.calendarView.setMinimumDate(min);
            holder.calendarView.setMaximumDate(max);

            holder.calendarView.setEvents(events);

            holder.calendarView.setDisabledDays(getDisabledDays());



        }

    /*    holder.calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(context,
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());*/
  /*      try {
            Calendar randomCalendar = getRandomCalendar();
            String text = randomCalendar.getTime().toString();
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            holder.calendarView.setDate(randomCalendar);
        } catch (OutOfDateRangeException exception) {
            exception.printStackTrace();

            Toast.makeText(context,
                    "Date is out of range",
                    Toast.LENGTH_LONG).show();
        }*/
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));

        return calendar;
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}