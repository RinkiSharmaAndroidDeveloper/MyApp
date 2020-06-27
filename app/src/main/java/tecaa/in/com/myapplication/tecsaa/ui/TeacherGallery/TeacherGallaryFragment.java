package tecaa.in.com.myapplication.tecsaa.ui.TeacherGallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import tecaa.in.com.myapplication.tecsaa.Adapters.GallaryTeacherAdapter;
import tecaa.in.com.myapplication.tecsaa.R;
import tecaa.in.com.myapplication.tecsaa.databinding.FragmentTeacherGallaryBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TeacherGallaryFragment extends Fragment {

    private GallaryViewModel gallaryViewModel;
    Context context;
    GallaryTeacherAdapter gallaryTeacherAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gallaryViewModel =
                ViewModelProviders.of(this).get(GallaryViewModel.class);
        FragmentTeacherGallaryBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_teacher_gallary, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider

        // bind RecyclerView
        RecyclerView recyclerView = binding.viewGallary;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        gallaryTeacherAdapter = new GallaryTeacherAdapter(getContext());
        recyclerView.setAdapter(gallaryTeacherAdapter);
        getAllEmployee();
        return view;
    }

    private void getAllEmployee() {
        gallaryViewModel.getAllEmployee().observe(this, new Observer<List<GalleryDataClass>>() {
            @Override
            public void onChanged(@Nullable List<GalleryDataClass> employees) {
                gallaryTeacherAdapter.setEmployeeList((ArrayList<GalleryDataClass>) employees);
            }
        });
    }

}
