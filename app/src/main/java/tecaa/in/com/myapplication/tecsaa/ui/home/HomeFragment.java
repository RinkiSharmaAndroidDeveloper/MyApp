package tecaa.in.com.myapplication.tecsaa.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import tecaa.in.com.myapplication.tecsaa.Adapters.SilderAdapters;
import tecaa.in.com.myapplication.tecsaa.AsyncResult;
import tecaa.in.com.myapplication.tecsaa.MainDashBoardActivity;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.ActivityTeacherFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.RequirtementFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherAttendanceFragment;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherClassWork;
import tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher.TeacherHomeWorkFragment;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;



public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SliderView sliderView;
    SilderAdapters adapter;
    Context context;
    ImageView homework,classWork,attendance,requirment,activty;
    AsyncResult<Fragment> asyncResult;
    public HomeFragment newInstance(Context context, AsyncResult<Fragment> asyncResult) {
        HomeFragment fragment = new HomeFragment();
         this.context  =context;
         this.asyncResult  =asyncResult;
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homework = root.findViewById(R.id.image_home);
        attendance = root.findViewById(R.id.attendence_image);
        classWork = root.findViewById(R.id.work_home_testing);
        activty = root.findViewById(R.id.activity_image);
        requirment = root.findViewById(R.id.requirment);
        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              setHomeWorkToActivity();
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAttendeanceToActivity();
            }
        });

        classWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setClassWorkToActivity();
            }
        });

        requirment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequirementActivity();
            }
        });

        activty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivity();
            }
        });
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });

        sliderView = root.findViewById(R.id.imageSlider_below);

        adapter = new SilderAdapters(context);
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(false);
        renewItems();
        return root;
    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }


    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    public void setClassWorkToActivity(){
        TeacherClassWork teacherClassWork =new TeacherClassWork();
        teacherClassWork.newInstance(getContext());
        asyncResult.success(teacherClassWork);
    }

    public void setRequirementActivity(){
       RequirtementFragment requirtementFragment =new RequirtementFragment();
        requirtementFragment.newInstance(getContext());
        asyncResult.success(requirtementFragment);
    }

    public void setActivity(){
        ActivityTeacherFragment activityTeacherFragment =new ActivityTeacherFragment();
        activityTeacherFragment.newInstance(getContext());
        asyncResult.success(activityTeacherFragment);
    }

    public void setAttendeanceToActivity(){
        TeacherAttendanceFragment attendanceFragment =new TeacherAttendanceFragment();
        attendanceFragment.newInstance(getContext());
        asyncResult.success(attendanceFragment);
    }

    public void setHomeWorkToActivity(){
        TeacherHomeWorkFragment teacherHomeWorkFragment =new TeacherHomeWorkFragment();
        teacherHomeWorkFragment.newInstance(getContext());
        asyncResult.success(teacherHomeWorkFragment);
    }
    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        adapter.addItem(sliderItem);
    }
}