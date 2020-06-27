package tecaa.in.com.myapplication.tecsaa.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;
import tecaa.in.com.myapplication.tecsaa.AsyncTwoValue;
import tecaa.in.com.myapplication.tecsaa.DrawableUtils;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.classWork.ClassWorkModel;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.AttendanceModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TeacherAttendanceAdapter extends RecyclerView.Adapter<TeacherAttendanceAdapter.MyViewHolder> {

    List<AttendanceModel> attendanceModels;
    Context context;
    AsyncTwoValue<AttendanceModel,String> asyncResult;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        Button edit,present,absent;

   RelativeLayout relativeLayout;

        public MyViewHolder(View view) {
            super(view);
            //title = (TextView) view.findViewById(R.id.title);
            name =  view.findViewById(R.id.name);
            edit =  view.findViewById(R.id.button_Edit);
            absent =  view.findViewById(R.id.button_absent);
            present =  view.findViewById(R.id.button_present);
            relativeLayout =  view.findViewById(R.id.back_bone);

        }
    }


    public TeacherAttendanceAdapter(Context context, List<AttendanceModel> attendanceModels, AsyncTwoValue<AttendanceModel,String> asyncResult) {
        this.context = context;
        this.attendanceModels = attendanceModels;
        this.asyncResult = asyncResult;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_list_attendance, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
         AttendanceModel attendanceModel =attendanceModels.get(position);
         holder.name.setText(attendanceModel.getFirstName() +" "+attendanceModel.getLastName());
         holder.edit.setVisibility(View.GONE);
         holder.present.setVisibility(View.VISIBLE);
         holder.absent.setVisibility(View.VISIBLE);
        holder.name.setTextColor(R.color.darkGray);
        if(attendanceModel.getType().equals("present"))
        {
            holder.absent.setVisibility(View.GONE);
            holder.present.setVisibility(View.GONE);
            holder.edit.setVisibility(View.VISIBLE);
            holder.relativeLayout.setBackgroundResource(R.color.green);
            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            holder.absent.setVisibility(View.GONE);
            holder.present.setVisibility(View.GONE);
            holder.edit.setVisibility(View.VISIBLE);
            holder.relativeLayout.setBackgroundResource(R.color.red);

            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
        }
        final int sdk = android.os.Build.VERSION.SDK_INT;

         holder.present.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 holder.absent.setVisibility(View.GONE);
                 holder.present.setVisibility(View.GONE);
                 holder.edit.setVisibility(View.VISIBLE);
                 holder.relativeLayout.setBackgroundResource(R.color.green);
                 holder.name.setTextColor(Color.parseColor("#FFFFFF"));
                 asyncResult.success(attendanceModels.get(position),"present");
             }
         });

         holder.absent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 holder.absent.setVisibility(View.GONE);
                 holder.present.setVisibility(View.GONE);
                 holder.edit.setVisibility(View.VISIBLE);
                 holder.relativeLayout.setBackgroundResource(R.color.red);

                 holder.name.setTextColor(Color.parseColor("#FFFFFF"));
                 asyncResult.success(attendanceModels.get(position),"absent");
             }
         });

         holder.edit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
              holder.absent.setVisibility(View.VISIBLE);
              holder.present.setVisibility(View.VISIBLE);
              holder.edit.setVisibility(View.GONE);
                 holder.name.setTextColor(R.color.darkGray);
                 if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                     holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.edit_rectanguler_box) );
                 } else {
                     holder.relativeLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.edit_rectanguler_box));
                 }
             }
         });

    }



    @Override
    public int getItemCount() {
        return attendanceModels.size();
    }
}